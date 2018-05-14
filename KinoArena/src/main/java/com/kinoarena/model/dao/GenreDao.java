package com.kinoarena.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.GenreRowMapper;
import com.kinoarena.model.vo.Genre;

@Component
public class GenreDao {
	private static final String SQL_GET_ALL_GENRES = "SELECT * FROM genres;";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private GenreRowMapper genreMapper;

	public List<Genre> getAllGenres(){
		List<Genre> genres = jdbcTemplate.query(SQL_GET_ALL_GENRES, new Object[] {}, genreMapper);
		return genres;
	}
	public Genre getGenreById(int id) throws Exception {
//		Genre genre = (Genre) jdbcTemplate.queryForObject("SELECT * FROM genres where id = ? ", new Object[] { id },
//				new GenreRowMapper());

		return null;

	}
}
