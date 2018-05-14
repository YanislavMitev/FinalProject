package com.kinoarena.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.HallRowMapper;
import com.kinoarena.model.vo.Hall;

@Component
public class HallDAO implements IHallDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HallRowMapper hallMapper;

	@Autowired
	private SeatDAO seatDao;

	@Override
	public List<Hall> getAllHalls() {

		return jdbcTemplate.query(SQL_GET_ALL_HALLS, hallMapper);
	}

	@Override
	public Hall getHallByName(String hallName, String cinemaName) {
		Hall hall = jdbcTemplate.queryForObject(SQL_GET_HALL_BY_NAME, new Object[] { hallName, cinemaName },
				hallMapper);
		return hall;
	}

	@Override
	public int getLastHallNumber(String hallName) {
		try {
			Hall hall = jdbcTemplate.queryForObject(SQL_GET_LAST_HALL, new Object[] { hallName }, hallMapper);
			return hall.getHallNumber();
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@Override
	public int getLastHallId() {
		try {
			Hall hall = jdbcTemplate.queryForObject(SQL_GET_LAST_HALL_BY_ID, new Object[] {}, hallMapper);
			return hall.getId();
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}

	@Override
	public void addHall(Hall hall) {
		jdbcTemplate.update(SQL_ADD_HALL, hall.getHallNumber(), hall.getCinema().getId(),
				hall.getHallType().getHallType());

		seatDao.addSeats(hall.getSeats());
	}

}
