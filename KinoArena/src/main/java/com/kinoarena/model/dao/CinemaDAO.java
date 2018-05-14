package com.kinoarena.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.CinemaRowMapper;
import com.kinoarena.model.vo.Cinema;

@Component
public class CinemaDAO implements ICinemaDAO {
	private static final String GET_ALL_CINEMAS = "SELECT * FROM cinema c JOIN address a ON(c.address_id = a.address_id);";

	private static final String GET_CINEMA_BY_ID = "SELECT * FROM cinema c JOIN address a ON(c.address_id = a.address_id) WHERE (c.cinema_id = ?)";

	private static final String SQL_ADD_CINEMA = "INSERT INTO cinema VALUES(null, ?, ?, ?, ?, ?, false);";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	CinemaRowMapper cinemaRowMapper;

	@Override
	public List<Cinema> getAllCinemas() {
		List<Cinema> cinemas = jdbcTemplate.query(GET_ALL_CINEMAS, cinemaRowMapper);
		return cinemas;

	}

	@Override
	public Cinema getCinemaById(int id) {
		try {
			Cinema cinema = jdbcTemplate.queryForObject(GET_CINEMA_BY_ID, new Object[] { id }, cinemaRowMapper);
			return cinema;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}


	public void addCinema(Cinema cinema) {
		if (cinema != null) {
			jdbcTemplate.update(SQL_ADD_CINEMA, cinema.getName(), cinema.getEmail(), cinema.getGsm(),
					cinema.getCinemaCoverURL(), cinema.getAddress().getId());
		}
	}

	@Override
	public Cinema getCinemaByName(String name) {
		Cinema cinema = jdbcTemplate.queryForObject(SQL_GET_CINEMA_BY_NAME, new Object[] {name}, cinemaRowMapper);
		return cinema;
	}
}
