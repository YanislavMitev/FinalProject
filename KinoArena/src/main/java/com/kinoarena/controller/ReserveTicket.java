package com.kinoarena.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.model.dao.ReservationDAO;
import com.kinoarena.model.dao.ScreeningDao;
import com.kinoarena.model.dao.SeatDAO;
import com.kinoarena.model.vo.ReservationTicketType;
import com.kinoarena.model.vo.Screening;
import com.kinoarena.model.vo.Seat;
import com.kinoarena.model.vo.Ticket;

@Controller
public class ReserveTicket {

	@Autowired
	ScreeningDao screeningDao;

	@Autowired
	ReservationDAO reservationDAO;
	@Autowired
	SeatDAO seatDao;

	@RequestMapping(method = RequestMethod.GET, value = "/reserve/{id}")
	public String reserveTicket(Model model, @PathVariable int id, HttpSession session) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				return "redirect:/login";
			}
			Screening screening = screeningDao.getScreeningById(id);
			if (screening == null) {
				return "redirect:/index";
			}
			List<ReservationTicketType> ticketType = reservationDAO.getTicketTypesByScreeningId(id);
			session.setAttribute("screening", screening);
			model.addAttribute("screening", screening);
			model.addAttribute("ticketTypes", ticketType);
			return "reserveTicket";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reserve", params = { "hallId" })
	public String procesTicketTypes(Model model, @RequestParam("hallId") int hallId, HttpSession session,
			HttpServletRequest request) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				return "redirect:/login";
			}
			int screeningId = ((Screening) session.getAttribute("screening")).getId();

			List<ReservationTicketType> ticketTypes = reservationDAO.getTicketTypesByScreeningId(screeningId);
			Map<String, List<ReservationTicketType>> reservedTypes = new HashMap<String, List<ReservationTicketType>>();
			int ticketNumbers = 0;
			for (ReservationTicketType type : ticketTypes) {
				int qty = Integer.parseInt(request.getParameter("qty" + type.getId()));
				for (int index = qty; index > 0; index--) {
					if (index == qty) {
						reservedTypes.put(type.getType(), new ArrayList<ReservationTicketType>());
					}
					reservedTypes.get(type.getType()).add(type);
					ticketNumbers++;
				}
				// here we validate the tickets
				if ((ticketNumbers > Ticket.MAX_TICKET_NUMBER) || (ticketNumbers <= Ticket.MIN_TICKET_NUMBER)) {
					model.addAttribute("ticketTypes", ticketTypes);
					model.addAttribute("error", "Invalid number of tickets");
					return "reserveTicket";
				}
			}
			session.setAttribute("ticketNumbers", ticketNumbers);
			session.setAttribute("reservedTicketTypes", reservedTypes);
			model.addAttribute("reservedTicketTypes", reservedTypes);
			model.addAttribute("ticketNumbers", ticketNumbers);
			List<Seat> reservedSeats = reservationDAO.getAllReservedSeatsByScreeningID(screeningId);
			Map<Byte, List<Seat>> seats = seatDao.getAllSeadsByHall(hallId);

			for (Entry<Byte, List<Seat>> row : seats.entrySet()) {
				for (Seat seat : row.getValue()) {
					if (reservedSeats.contains(seat)) {
						seat.setTaken(true);
					}
				}
			}
			model.addAttribute("seats", seats);
			return "seats";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/finalize")
	public String finalizeReservation(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam Map<String, String> allRequestParams) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				return "redirect:/login";
			}
			Screening screening = (Screening) session.getAttribute("screening");
			int screeningId = screening.getId();
			List<Integer> reservedSeatsIDs = new ArrayList<Integer>();
			for (Entry<String, String> entry : allRequestParams.entrySet()) {
				if (entry.getValue().length() > 0) {
					reservedSeatsIDs.add(Integer.parseInt(entry.getValue()));
				}
			}
			if (reservedSeatsIDs.size() == 0) {
				return "redirect:/reserve" + screening.getHall().getId();
			}
			System.out.println(reservedSeatsIDs);
			reservationDAO.reserveSeats(reservedSeatsIDs, screeningId);
			int firstSeatId = reservedSeatsIDs.get(0);
			int lastSeatId = reservedSeatsIDs.get(reservedSeatsIDs.size() - 1);

			List<Seat> seats = reservationDAO.getReservedSeatsBySeatIdAndScreeningId(firstSeatId, lastSeatId,
					screeningId);
			List<Ticket> tickets = new ArrayList<Ticket>();
			UserDTO user = (UserDTO) session.getAttribute("loggedUser");
			for (Seat seat : seats) {
				tickets.add(new Ticket(user, screening, seat, true, false));
			}
			Map<String, List<ReservationTicketType>> reservedTypes = (Map<String, List<ReservationTicketType>>) session
					.getAttribute("reservedTicketTypes");
			int index = 0;
			System.out.println(reservedTypes.toString());
			for (Entry<String, List<ReservationTicketType>> entry : reservedTypes.entrySet()) {
				for (ReservationTicketType type : entry.getValue()) {
					System.out.println(type);
					Ticket ticket = tickets.get(index);
					ticket.setTicketType(type);
					index++;
				}
			}
			System.out.println(seats.toString());

			System.out.println(tickets);
			reservationDAO.addTickets(tickets);

			return "finalizeReservation";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
