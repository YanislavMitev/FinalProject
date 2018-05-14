package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Ticket;

@Component
public class TicketRowMapper implements RowMapper<Ticket> {
	@Autowired
	UserDtoRowMapper userDtoRowMapper;
	@Autowired
	ScreeningRowMapper screeningRowMapper;
	@Autowired
	SeatRowMapper seatRowMapper;
@Autowired
ReservationTicketTypeRowMapper ticketTypeRowMapper;
	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = null;
		try {
			ticket = new Ticket( rs.getInt("ticket_id"),userDtoRowMapper.mapRow(rs, rowNum), screeningRowMapper.mapRow(rs, rowNum),
					seatRowMapper.mapRow(rs, rowNum), rs.getBoolean("active"), rs.getBoolean("paid"),
					ticketTypeRowMapper.mapRow(rs, rowNum));

			return ticket;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
