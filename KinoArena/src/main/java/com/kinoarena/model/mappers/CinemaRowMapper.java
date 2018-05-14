package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Cinema;

@Component
public class CinemaRowMapper implements RowMapper<Cinema> {
	@Autowired
	private AddressRowMapper addressMapper;

	@Override
	public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cinema cinema = null;
		try {
			cinema = new Cinema(rs.getInt("cinema_id"),rs.getString("name"), rs.getString("email"), rs.getString("gsm"),
					addressMapper.mapRow(rs, rowNum));
			
			if(rs.getString("cinemaCoverURL") != null) {
				cinema.setCinemaCoverURL(rs.getString("cinemaCoverURL"));
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}

		return cinema;
	}

}
