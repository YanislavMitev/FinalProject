package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.dto.UserDTO;

@Component
public class UserDtoRowMapper implements RowMapper<UserDTO> {

	@Autowired
	private AddressRowMapper addressMapper;

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDTO user = null;
		try {
			user = new UserDTO(rs.getInt("user_id"), rs.getString("email"), rs.getString("firstName"),
					rs.getString("secondName"), rs.getString("lastName"), rs.getBoolean("isMale"),
					rs.getDate("birthday").toLocalDate(), rs.getBoolean("isAdmin"));

			if (rs.getObject("gsm") != null) {
				user.setGSM(rs.getString("gsm"));
			}
			if (rs.getObject("address_id") != null) {
				user.setAddress(addressMapper.mapRow(rs, rowNum));
			}
			if (rs.getObject("job") != null) {
				user.setJob(rs.getString("job"));
			}
			if (rs.getObject("education") != null) {
				user.setJob(rs.getString("education"));
			}
			return user;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
