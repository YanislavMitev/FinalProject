package com.kinoarena.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.MovieRowMapper;
import com.kinoarena.model.vo.Movie;

@Component
public class FavoriteMovieDAO {
	private static final String ADD_FAVORITE_MOVIE = "INSERT INTO favoritemovies(users_id, movie_id) VALUES(?,?);";
	private static final String GET_FAVORITE_MOVIES = "SELECT m.*,g.* FROM favoritemovies f "
			+ "JOIN movies m ON(f.movie_id = m.movie_id) " + "LEFT OUTER JOIN genres g ON(m.genres_id = g.genre_id) "
			+ "WHERE (f.users_id = ?);";
	private static final String GET_FAVORITE_MOVIE = "SELECT m.*,g.* FROM favoritemovies f "
			+ "JOIN movies m ON(f.movie_id = m.movie_id) " + "LEFT OUTER JOIN genres g ON(m.genres_id = g.genre_id) "
			+ "WHERE (f.users_id = ?) AND (f.movie_id = ?);";
	private static final String DELETE_FAVORITE_MOVIE = "DELETE FROM favoritemovies WHERE(users_id = ?)AND(movie_id = ?);";;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MovieRowMapper movieRowMapper;

	public void addFavoriteMovie(int userID, int movieID) {
		jdbcTemplate.update(ADD_FAVORITE_MOVIE, userID, movieID);
	}

	public void removeFavoriteMovie(int userID, int movieID) {
		jdbcTemplate.update(DELETE_FAVORITE_MOVIE, userID, movieID);
	}

	public List<Movie> getFavoriteMovies(int userID) {
		List<Movie> movies = jdbcTemplate.query(GET_FAVORITE_MOVIES, new Object[] { userID }, movieRowMapper);
		return movies;
	}

	public Movie getFavoriteMovie(int userID, int movieID) {
		try {
			Movie movie = jdbcTemplate.queryForObject(GET_FAVORITE_MOVIE, new Object[] { userID, movieID },
					movieRowMapper);
			return movie;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
