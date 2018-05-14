package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Movie;

@Component
public class MovieRowMapper implements RowMapper<Movie> {
	@Autowired
	private GenreRowMapper genreMapper;

	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = null;
		try {
			movie = new Movie(rs.getInt("movie_id"), rs.getString("title"), rs.getString("coverURL"), rs.getInt("duration"));
			movie.setDescription(rs.getString("description"));
			
			movie.setPrimiere(rs.getDate("premiere"));
			movie.setAgeLimitation(rs.getInt("ageLimitation"));
			movie.setDirector(rs.getString("director"));
			movie.setMovieType(rs.getString("movieType"));
			movie.setGenre(genreMapper.mapRow(rs, rowNum));
		} catch (Exception e) {
			throw new SQLException(e);
		}

		return movie;
	}

}
