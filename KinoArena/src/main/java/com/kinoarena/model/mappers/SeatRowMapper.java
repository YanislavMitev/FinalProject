package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Seat;

@Component
public class SeatRowMapper implements RowMapper<Seat> {
	@Autowired
	HallRowMapper hallRowMapper;

	@Override
	public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seat seat = null;
		try {
			seat = new Seat(rs.getInt("seat_id"), rs.getInt("row"), rs.getInt("number"),
					hallRowMapper.mapRow(rs, rowNum));
			return seat;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
