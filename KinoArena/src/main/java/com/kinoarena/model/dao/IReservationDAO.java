package com.kinoarena.model.dao;

import java.util.List;

import com.kinoarena.model.vo.ReservationTicketType;
import com.kinoarena.model.vo.Seat;
import com.kinoarena.model.vo.Ticket;

public interface IReservationDAO {

	void reserveSeats(List<Integer> seats, int screeningId);

	List<Seat> getAllReservedSeatsByScreeningID(int id) throws Exception;

	List<ReservationTicketType> getTicketTypesByScreeningId(int id);

	List<ReservationTicketType> getTicketTypes();

	List<Seat> getReservedSeatsBySeatIdAndScreeningId(int firstSeatId, int lastSeatId, int screeningId)
			throws Exception;

	void addTickets(List<Ticket> tickets);

	List<Ticket> getTickets(int userId);

}
