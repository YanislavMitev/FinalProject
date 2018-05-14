package com.kinoarena.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kinoarena.model.vo.User;

@Component
public class UserRowMapper implements RowMapper<User> {

	@Autowired
	private AddressRowMapper addressMapper;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = null;
		try {
			user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"),
					rs.getString("firstName"), rs.getString("secondName"), rs.getString("lastName"),
					rs.getBoolean("isMale"), rs.getDate("birthday").toLocalDate());
			
			user.setAdminRights(rs.getBoolean("isAdmin"));
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
