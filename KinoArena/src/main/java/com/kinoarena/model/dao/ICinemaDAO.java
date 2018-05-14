package com.kinoarena.model.dao;

import java.util.Collection;

import com.kinoarena.model.vo.Cinema;

public interface ICinemaDAO {
	
	public static final String SQL_GET_CINEMA_BY_NAME = "SELECT * FROM cinema c JOIN address a ON(c.address_id = a.address_id) WHERE c.name = ?;";
	public Collection<Cinema> getAllCinemas() throws Exception;

	public Cinema getCinemaById(int id);
	
	public void addCinema(Cinema cinema);
	
	public Cinema getCinemaByName(String name);

}
