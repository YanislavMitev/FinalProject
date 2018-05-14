package com.kinoarena.model.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.exceptions.MovieException;
import com.kinoarena.model.mappers.GenreRowMapper;
import com.kinoarena.model.mappers.MovieRowMapper;
import com.kinoarena.model.vo.Genre;
import com.kinoarena.model.vo.Hall;
import com.kinoarena.model.vo.Movie;

@Component
public class MovieDao implements IMovieDao {

	private static final String GET_MOVIES = "SELECT * FROM movies m JOIN genres g ON(m.genres_id = g.genre_id);";
	private static final String GET_ACTIVE_MOVIES = "SELECT m.*,g.* FROM movies m JOIN screening s ON(s.movie_id=m.movie_id) LEFT OUTER jOIN genres g ON(m.genres_id=g.genre_id) WHERE(DATE(s.startTime) > DATE(?)) GROUP BY (m.movie_id);";
	private static final String GET_ACTIVE_MOVIES_BY_GENRE = "SELECT m.*,g.* FROM movies m JOIN screening s ON(s.movie_id=m.movie_id) LEFT OUTER jOIN genres g ON(m.genres_id=g.genre_id) WHERE(g.genre = ? ) "
			+ "AND (DATE(s.startTime) >= DATE(?)) GROUP BY (m.movie_id);";
	private static final String GET_ACTIVE_MOVIES_BY_TITLE = "SELECT m.*,g.* FROM movies m JOIN screening s ON(s.movie_id=m.movie_id) LEFT OUTER jOIN genres g ON(m.genres_id=g.genre_id) WHERE(m.title LIKE(?) ) "
			+ "AND (s.startTime >= DATE(?)) GROUP BY (m.movie_id);";
	private static final String GET_ACTIVE_MOVIES_BY_HALL = "SELECT m.*,g.* FROM movies m "
			+ "JOIN screening s ON(s.movie_id=m.movie_id) LEFT OUTER jOIN " + " genres g ON(m.genres_id=g.genre_id) "
			+ " JOIN halls h ON(s.halls_id=h.hall_id) WHERE(h.hallType = ?) AND (s.startTime >= ?) GROUP BY (m.movie_id);";
	private static final String GET_MOVIE_BY_ID = "SELECT m.*,g.* FROM movies m LEFT OUTER jOIN genres g ON(m.genres_id=g.genre_id) WHERE (m.movie_id = ? );";
	private static final String SQL_INSERT_MOVIE = "INSERT INTO movies VALUES(null, ?, ?, ?, ?, ?, DATE(?), ?, ?, ?, false);";
	private static final String SQL_GET_MOVIE_BY_TITLE = "SELECT * FROM movies m JOIN genres g ON (m.genres_id = g.genre_id) WHERE title = ?;";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MovieRowMapper movieRowMapper;
	@Autowired
	private GenreRowMapper genreRowMapper;

	@Override

	public void addMovie(Movie movie) throws Exception {
		if (movie != null) {
			System.out.println(movie.getGenre().getId());
			jdbcTemplate.update(SQL_INSERT_MOVIE, movie.getTitle(), movie.getCoverURL(), movie.getDescription(),
					movie.getDirector(), movie.getDuration(), movie.getPrimiere(), movie.getAgeLimitation(),
					movie.getMovieType(), movie.getGenre().getId());
		} else
			throw new MovieException("Null movie.");
	}

	@Override
	public void editMovie(Movie movie) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMovie(Movie movie) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Movie> getAllMovies() throws Exception {
		List<Movie> movies = jdbcTemplate.query(GET_MOVIES, movieRowMapper);
		return movies;
	}

	@Override
	public Movie getMovieById(int id) {
		try {
			Movie movie = jdbcTemplate.queryForObject(GET_MOVIE_BY_ID, new Object[] { id }, movieRowMapper);
			return movie;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Movie> getActiveMoviesByTitle(String title) {
		List<Movie> movies = jdbcTemplate.query(GET_ACTIVE_MOVIES_BY_TITLE,
				new Object[] { title + "%", Timestamp.valueOf(LocalDateTime.now()) }, movieRowMapper);
		return movies;
	}

	public List<Movie> getActiveMoviesByHallType(String hall) {
		List<Movie> movies = jdbcTemplate.query(GET_ACTIVE_MOVIES_BY_HALL,
				new Object[] { hall, Timestamp.valueOf(LocalDateTime.now()) }, movieRowMapper);
		return movies;
	}

	public List<Movie> getActiveMoviesByGenre(String genre) {
		List<Movie> movies = jdbcTemplate.query(GET_ACTIVE_MOVIES_BY_GENRE,
				new Object[] { genre, Timestamp.valueOf(LocalDateTime.now()) }, movieRowMapper);
		return movies;
	}

	public List<Movie> getActiveMovies() {
		List<Movie> movies = jdbcTemplate.query(GET_ACTIVE_MOVIES,
				new Object[] { Timestamp.valueOf(LocalDateTime.now()) }, movieRowMapper);
		return movies;
	}

	@Override
	public Genre getGenre(String genre) {
		try {
		Genre genreObj = (Genre) jdbcTemplate.queryForObject("SELECT * FROM genres WHERE genre = ?;",
				new Object[] { genre }, genreRowMapper);
		
		return genreObj;}catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	@Override
	public Movie getMovieByName(String name) {
		try {
		Movie movie = (Movie) (jdbcTemplate.queryForObject(SQL_GET_MOVIE_BY_TITLE, new Object[] { name },
				movieRowMapper));
		return movie;}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
