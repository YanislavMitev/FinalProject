package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.dto.ScreeningDTO;

@Component
public class ScreeningDtoRowMapper implements RowMapper<ScreeningDTO> {

	public ScreeningDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ScreeningDTO screening = null;
		try {
			screening = new ScreeningDTO(rs.getInt("screening_id"), rs.getTimestamp("startTime").toLocalDateTime(),
					rs.getInt("movie_id"), rs.getInt("halls_id"));

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return screening;
	}
}
