package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.enums.HallType;
import com.kinoarena.model.vo.Hall;

@Component
public class HallRowMapper implements RowMapper<Hall> {
	@Autowired
	private CinemaRowMapper cinemaRowMapper;

	public Hall mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hall hall = null;
		try {
			hall = new Hall(rs.getInt("hall_id"), rs.getInt("hallNumber"), cinemaRowMapper.mapRow(rs, rowNum));

			hall.setHallType(HallType.valueOf(rs.getString("hallType").toUpperCase()));

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return hall;
	}
}
