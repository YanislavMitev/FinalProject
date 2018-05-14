package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.Address;

@Component
public class AddressRowMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address = null;
		try {
			address = new Address(rs.getInt("address_id"), rs.getString("address"), rs.getString("postCode"), rs.getString("city"));
			
		} catch (Exception e) {
			throw new SQLException(e);
		}

		return address;
	}

}
