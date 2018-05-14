package com.kinoarena.model.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.exceptions.WebProfileException;
import com.kinoarena.model.mappers.AddressRowMapper;
import com.kinoarena.model.mappers.UserDtoRowMapper;
import com.kinoarena.model.vo.Address;
import com.kinoarena.model.vo.Movie;
import com.kinoarena.model.vo.User;

@Component
public class UserDAO implements IUserDAO {

	private static final String INVALID_CREDENTIALS = "Invalid credentials.";
	public static final String SQL_LOGIN_STATEMENT = "SELECT * FROM users u LEFT OUTER JOIN address a ON(u.address_id = a.address_id) WHERE u.email = ? AND u.password = sha1(?) AND u.userDeleted = false;";
	public static final String SQL_CHANGE_PASSWORD_STATEMENT = "UPDATE users SET password = sha1(?) WHERE email = ?;";
	public static final String SQL_ADD_USER = "INSERT INTO users(user_id, email, password, firstName, secondName, lastName, isMale, birthday, isAdmin) VALUES(null, ?, sha1(?), ?, ?, ?, ?, DATE(?), 0);";
	public static final String SQL_MAKE_ADMIN = "UPDATE users SET isAdmin = ? WHERE email = ? ;";
	public static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users u LEFT OUTER JOIN address a ON(u.address_id = a.address_id) WHERE u.email = ?;";
	public static final String SQL_GET_ADDRESS = "SELECT * FROM address WHERE address = ?;";
	public static final String SQL_INSERT_ADDRESS = "INSERT INTO address VALUES(null, ?, ?, ?);";
	public static final String SQL_UPDATE_USER_INFO = "UPDATE users SET firstName = ?, secondName = ?, lastName = ?, isMale = ?, birthday = ?, gsm = ?, education = ?, job = ?,address_id = ?, isAdmin = ? WHERE user_id = ?;";
	public static final String SQL_GET_ALL_USERS = "SELECT * FROM users u JOIN address a ON (u.address_id = a.address_id);";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDtoRowMapper userMapper;

	@Autowired
	private AddressRowMapper addressMapper;
	
	@Autowired
	private AddressDao addressDao;
	
	
	@Override
	public UserDTO login(String email, String password) throws WebProfileException {
		try {
			UserDTO user = jdbcTemplate.queryForObject(SQL_LOGIN_STATEMENT, new Object[] { email, password },
					userMapper);
			return user;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(INVALID_CREDENTIALS);
			throw new WebProfileException(INVALID_CREDENTIALS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(INVALID_CREDENTIALS);
			throw new WebProfileException(INVALID_CREDENTIALS, e);
		}
	}

	@Override
	public void register(String firstName, String secondName, String lastName, String email, String password,
			boolean isMale, LocalDate dateOfBirth) {

		jdbcTemplate.update(SQL_ADD_USER, email, password, firstName, secondName, lastName, isMale,
				dateOfBirth);
	}

	@Override
	public void changePassword(UserDTO user, String reNewPass) {
		jdbcTemplate.update("UPDATE users SET password = sha1(?) WHERE email = ?;", reNewPass, user.getEmail());
		System.out.println(user.getEmail());
		System.out.println("User password updated!");
	}

	public void makeUserAdmin(String email) {
		jdbcTemplate.update(SQL_MAKE_ADMIN, true, email);
	}

	@Override
	public void editUser(UserDTO user) {
		String streetAddress = user.getAddress().getAddress();
		String postcode = user.getAddress().getPostcode();
		String city = user.getAddress().getCity();
		Address address = addressDao.getAddress(streetAddress, postcode, city);
		
		jdbcTemplate.update(SQL_UPDATE_USER_INFO, user.getFirstName(), user.getSecondName(), user.getLastName(),
				user.isMale(), user.getBirthday(), user.getGsm(), user.getEducation(), user.getJob(), address.getId(),
				user.isAdmin(), user.getId());

	}

	public List<UserDTO> getAllUsers(){
		List<UserDTO> users = jdbcTemplate.query(SQL_GET_ALL_USERS,
				new Object[] { }, userMapper);
		
		return users;
	}

	@Override
	public int getUserId(String email) {

		UserDTO user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_EMAIL, new Object[] { email }, userMapper);

		return user.getId();
	}

}
