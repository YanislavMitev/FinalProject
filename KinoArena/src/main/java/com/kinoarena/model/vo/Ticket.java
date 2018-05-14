package com.kinoarena.model.vo;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.exceptions.ModelException;

public class Ticket {
	public static final int MAX_TICKET_NUMBER = 6;
	public static final int MIN_TICKET_NUMBER = 0;

	private static final String CANNOT_SET_RESERVATION = "Cannot set reservation to user.";
	private static final String INVALID_MOVIE = "Invalid movie entered.";
	private static final String INVALID_SEAT = "Invalid seat entered.";
	private static final String SEAT_IS_TAKEN = "Seat is taken";
	private int id;
	private UserDTO user;
	private Screening screening;
	private Seat seat;
	private ReservationTicketType ticketType;
	private boolean isActive;
	private boolean isPaid;

	public Ticket(UserDTO user, Screening screening, Seat seat, boolean active, boolean paid) throws ModelException {
		setUser(user);
		setScreening(screening);
		setSeat(seat);
		setActive(active);
		setPaid(paid);
	}

	public Ticket(int id,UserDTO user, Screening screening, Seat seat, boolean active, boolean paid,
			ReservationTicketType ticketType) throws ModelException {
		this(user, screening, seat, active, paid);
		setId(id);
		setTicketType(ticketType);
	}

	public void setSeat(Seat seat) throws ModelException {
		if (seat != null) {
			this.seat = seat;
		} else
			throw new ModelException(INVALID_SEAT);
	}

	public Seat getSeat() {
		return this.seat;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public ReservationTicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(ReservationTicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "Ticket [user=" + user + ", screening=" + screening + ", seat=" + seat + ", ticketType=" + ticketType
				+ ", isActive=" + isActive + ", isPaid=" + isPaid + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
