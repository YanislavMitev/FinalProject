package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.ReservationTicketType;

@Component
public class ReservationTicketTypeRowMapper implements RowMapper<ReservationTicketType> {

	public ReservationTicketType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationTicketType screservationTicketType = null;
		try {
			screservationTicketType = new ReservationTicketType(rs.getInt("reservationType_id"), rs.getString("type"),
					rs.getFloat("price"));

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return screservationTicketType;
	}
}
