package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Screening;

@Component
public class ScreeningRowMapper implements RowMapper<Screening> {

	@Autowired
	private MovieRowMapper movieRowMapper;
	@Autowired
	private HallRowMapper hallMapper;

	public Screening mapRow(ResultSet rs, int rowNum) throws SQLException {
		Screening screening = null;
		try {
			screening = new Screening(rs.getInt("screening_id"), rs.getTimestamp("startTime").toLocalDateTime(),
					movieRowMapper.mapRow(rs, rowNum), hallMapper.mapRow(rs, rowNum));
			return screening;
		} catch (Exception e) {
			throw new SQLException(e);
		}

	}

}
