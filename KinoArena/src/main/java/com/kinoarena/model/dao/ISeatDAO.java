package com.kinoarena.model.dao;

import java.util.List;
import java.util.Map;

import com.kinoarena.model.vo.Seat;

public interface ISeatDAO {
	Map<Byte, List<Seat>> getAllSeadsByHall(int id) throws Exception;

//	public int getLastSeatId();

	public void addSeats(final List<Seat> seat);

}
