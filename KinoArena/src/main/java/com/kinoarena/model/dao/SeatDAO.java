package com.kinoarena.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.SeatRowMapper;
import com.kinoarena.model.vo.Seat;

@Component
public class SeatDAO implements ISeatDAO {
	public static final String SQL_ADD_SEAT = "INSERT INTO seat VALUES(null, ?, ?, ?,0);";

	private static final String GET_LAST_SEAT = "SELECT * FROM seat s JOIN halls h ON (s.halls_id = h.hall_id) JOIN cinema c ON (h.cinema_id = c.cinema_id) JOIN address a ON (c.address_id = a.address_id) WHERE(s.seatDeleted=false) ORDER BY seat_id DESC LIMIT 1;";

	private static final String GET_SEATS_BY_HALL = "SELECT * FROM seat s " + "JOIN halls h ON(s.halls_id = h.hall_id) "
			+ "JOIN cinema c ON(h.cinema_id=c.cinema_id) "
			+ "JOIN address a ON(a.address_id = c.address_id) WHERE(h.hall_id = ?);";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SeatRowMapper seatRowMapper;

	@Override
	public Map<Byte, List<Seat>> getAllSeadsByHall(int id) throws Exception {
		List<Seat> seats = jdbcTemplate.query(GET_SEATS_BY_HALL, new Object[] { id }, seatRowMapper);
		Map<Byte, List<Seat>> sortedSeats = new HashMap<Byte, List<Seat>>();
		for (Seat seat : seats) {
			if (!sortedSeats.containsKey(seat.getRow())) {
				sortedSeats.put(seat.getRow(), new LinkedList<Seat>());
			}
			sortedSeats.get(seat.getRow()).add(seat);
		}

		return sortedSeats;
	}
	@Override
	public void addSeats(final List<Seat> seats) {
		System.out.println(seats.toString());
		jdbcTemplate.batchUpdate(SQL_ADD_SEAT, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Seat seat = seats.get(i);
				ps.setInt(1, seat.getRow());
				ps.setInt(2, seat.getNumber());
				ps.setInt(3, seat.getHall().getId());
			}

			@Override
			public int getBatchSize() {
				return seats.size();
			}
		});
	}
}
