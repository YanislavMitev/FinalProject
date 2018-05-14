package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Genre;

@Component
public class GenreRowMapper implements RowMapper<Genre> {
	public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = null;
		try {
			genre = new Genre(rs.getInt("genre_id"), rs.getString("genre"));

		} catch (Exception e) {
			throw new SQLException(e);
		}
		return genre;
	}
}