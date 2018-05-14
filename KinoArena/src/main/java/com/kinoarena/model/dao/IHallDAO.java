package com.kinoarena.model.dao;

import java.util.List;

import com.kinoarena.model.vo.Hall;

public interface IHallDAO {
	public static final String SQL_GET_ALL_HALLS = "SELECT * FROM halls h JOIN cinema c ON (h.cinema_id = c.cinema_id) JOIN address a ON (c.address_id = a.address_id) WHERE(h.hallDeleted = false) ORDER BY c.name DESC, hallNumber";
	public static final String SQL_GET_HALL_BY_NAME = "SELECT * FROM halls h JOIN cinema c ON (h.cinema_id = c.cinema_id) JOIN address a ON (c.address_id = a.address_id) WHERE hallNumber = ? AND c.name = ?;";
	public static final String SQL_GET_LAST_HALL = "SELECT * FROM halls h JOIN cinema c ON (h.cinema_id = c.cinema_id) JOIN address a ON (c.address_id = a.address_id) WHERE c.name = ? ORDER BY hallNumber DESC LIMIT 1;";
	public static final String SQL_GET_LAST_HALL_BY_ID = "SELECT * FROM halls h JOIN cinema c ON(h.cinema_id = c.cinema_id) JOIN address a ON (c.address_id = a.address_id) WHERE(h.hallDeleted = false) ORDER BY h.hall_id DESC LIMIT 1;";
	public static final String SQL_ADD_HALL = "INSERT INTO halls VALUES(null, ?, ?, ?, false);";
	
	public List<Hall> getAllHalls();
	
	public Hall getHallByName(String hallName, String cinemaName);
	
	public int getLastHallNumber(String hallName);
	
	public int getLastHallId();
	
	public void addHall(Hall hall);
}
