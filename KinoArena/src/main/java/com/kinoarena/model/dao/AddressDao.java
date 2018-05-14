package com.kinoarena.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.model.mappers.AddressRowMapper;
import com.kinoarena.model.vo.Address;

@Component
public class AddressDao implements IAddressDAO {
	private static final String GET_ADRESSES = "SELECT * FROM address a JOIN city c ON(a.city_id = c.id);";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AddressRowMapper addressRowMapper;

	
	@Override
	public List<Address> getAllAddresses() throws Exception {
		List<Address> addresses = jdbcTemplate.query(GET_ADRESSES, addressRowMapper);
		return addresses;
	}
	@Override
	public Address getAddress(String streetAddress, String postcode, String city) {
		Address address;
		try {
			address = jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new Object[] { streetAddress }, addressRowMapper);

			return address;

		} catch (Exception e) {

			jdbcTemplate.update(SQL_INSERT_ADDRESS, streetAddress, postcode, city);
			address = jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new Object[] { streetAddress }, addressRowMapper);
			return address;

		}
	}

}
