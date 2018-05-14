package com.kinoarena.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.ReservationTicketTypeRowMapper;
import com.kinoarena.model.mappers.ReserveSeatRowMapper;
import com.kinoarena.model.mappers.SeatRowMapper;
import com.kinoarena.model.mappers.TicketRowMapper;
import com.kinoarena.model.vo.ReservationTicketType;
import com.kinoarena.model.vo.Seat;
import com.kinoarena.model.vo.Ticket;

@Component
public class ReservationDAO implements IReservationDAO {

	private static final String GET_TICKETTYPE_BY_SCREENING_ID = "SELECT r.* FROM reservationtype r "
			+ "JOIN  screening_has_reservationtype shr ON(r.reservationType_id =shr.reservationType_id) "
			+ "JOIN screening s ON(shr.screening_id = s.screening_id) WHERE (s.screening_id = ?);";
	private static final String RESERVE_SEATS = "INSERT INTO reservedseat VALUES(null,?,?,false)";
	private static final String GET_RESERVED_SEATS_BY_SCREENING = "SELECT * FROM reservedseat rs "
			+ "JOIN seat s ON(rs.seat_id = s.seat_id) " + "JOIN halls h ON(s.halls_id = h.hall_id) "
			+ "JOIN cinema c ON(h.cinema_id=c.cinema_id) "
			+ "JOIN address a ON(a.address_id = c.address_id) WHERE(rs.screening_id = ?);";
	private static final String GET_TICKETTYPE = "SELECT * FROM reservationtype;";
	private static final String GET_RESERVED_SEATS_BY_SCREENING_AND_SEAT_IDS = "SELECT * FROM reservedseat rs "
			+ "JOIN seat s ON(rs.seat_id = s.seat_id) " + "JOIN halls h ON(s.halls_id = h.hall_id) "
			+ "JOIN cinema c ON(h.cinema_id=c.cinema_id) "
			+ "JOIN address a ON(a.address_id = c.address_id) WHERE((rs.seat_id BETWEEN ? AND ?)AND(rs.screening_id = ?));";
	private static final String ADD_TICKETS = "INSERT INTO tickets VALUES(null,true,false,?,?,?,?);";
	private static final String GET_TICKETS_BY_USER_ID = "SELECT * FROM tickets t JOIN reservedseat rs ON (t.reservedSeat_id = rs.reservedSeat_id)"
			+ "JOIN seat s ON(rs.seat_id = s.seat_id) " + "JOIN halls h ON(s.halls_id = h.hall_id) "
			+ "JOIN cinema c ON(h.cinema_id=c.cinema_id) "
			+ "JOIN address a ON(a.address_id = c.address_id) JOIN screening sc ON(sc.screening_id = t.screening_id) JOIN movies m ON(sc.movie_id = m.movie_id) "
			+ "JOIN genres g ON(m.genres_id = g.genre_id) JOIN users u ON(t.user_id = u.user_id) JOIN screening_has_reservationtype srh ON(sc.screening_id = srh.screening_id) "
			+ " JOIN reservationtype re ON(re.reservationType_id = srh.reservationType_id) WHERE(t.user_id = ? );";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	SeatRowMapper seatRowMapper;
	@Autowired
	ReservationTicketTypeRowMapper ticketTypeRowMapper;
	@Autowired
	ReserveSeatRowMapper reservedSeatRowMapper;
	@Autowired
	TicketRowMapper ticketRowMapper;

	@Override
	public List<ReservationTicketType> getTicketTypes() {
		List<ReservationTicketType> ticketTypes = jdbcTemplate.query(GET_TICKETTYPE, ticketTypeRowMapper);
		return ticketTypes;
	}

	@Override
	public List<ReservationTicketType> getTicketTypesByScreeningId(int id) {
		List<ReservationTicketType> ticketTypes = jdbcTemplate.query(GET_TICKETTYPE_BY_SCREENING_ID,
				new Object[] { id }, ticketTypeRowMapper);
		return ticketTypes;
	}

	// reserve number of seats
	@Override
	public void reserveSeats(final List<Integer> seatsIDs, int screeningId) {

		jdbcTemplate.batchUpdate(RESERVE_SEATS, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer seatId = seatsIDs.get(i);
				ps.setInt(1, seatId);
				ps.setInt(2, screeningId);
			}

			@Override
			public int getBatchSize() {
				return seatsIDs.size();
			}
		});
	}

	@Override
	public List<Seat> getAllReservedSeatsByScreeningID(int id) throws Exception {
		List<Seat> seats = jdbcTemplate.query(GET_RESERVED_SEATS_BY_SCREENING, new Object[] { id }, seatRowMapper);
		return seats;
	}

	@Override
	public List<Seat> getReservedSeatsBySeatIdAndScreeningId(int firstSeatId, int lastSeatId, int screeningId)
			throws Exception {
		List<Seat> seats = jdbcTemplate.query(GET_RESERVED_SEATS_BY_SCREENING_AND_SEAT_IDS,
				new Object[] { firstSeatId, lastSeatId, screeningId }, reservedSeatRowMapper);
		return seats;
	}

	@Override
	public List<Ticket> getTickets(int userId) {
		List<Ticket> tickets = jdbcTemplate.query(GET_TICKETS_BY_USER_ID, new Object[] { userId }, ticketRowMapper);
		return tickets;
	}

	// reserve number of seats
	@Override
	public void addTickets(final List<Ticket> tickets) {

		jdbcTemplate.batchUpdate(ADD_TICKETS, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Ticket ticket = tickets.get(i);
				ps.setInt(1, ticket.getUser().getId());
				ps.setInt(2, ticket.getSeat().getId());
				ps.setInt(3, ticket.getTicketType().getId());
				ps.setInt(4, ticket.getScreening().getId());

			}

			@Override
			public int getBatchSize() {
				return tickets.size();
			}
		});
	}

}
