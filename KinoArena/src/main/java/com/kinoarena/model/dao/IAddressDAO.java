package com.kinoarena.model.dao;

import java.util.List;

import com.kinoarena.model.vo.Address;

public interface IAddressDAO {

	public static final String SQL_GET_ADDRESS = "SELECT * FROM address WHERE address = ?;";
	public static final String SQL_INSERT_ADDRESS = "INSERT INTO address VALUES(null, ?, ?, ?, false);";

	public List<Address> getAllAddresses() throws Exception;

	public Address getAddress(String streetAddress, String postcode, String city);

}