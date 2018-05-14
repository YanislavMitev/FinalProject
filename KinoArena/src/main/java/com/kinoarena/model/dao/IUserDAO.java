package com.kinoarena.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.exceptions.WebProfileException;
import com.kinoarena.model.vo.User;

public interface IUserDAO {
	
	public UserDTO login(String email, String password) throws WebProfileException;
	
	public void register(String fisrtName, String secondName, String lastName,
						 String email, String password,
						 boolean isMale, LocalDate dateOfBirth);
	
	public void changePassword(UserDTO user, String reNewPass);
	
	public void makeUserAdmin(String email);
	
	public void editUser(UserDTO user);
	
	public int getUserId(String email);
	
	public List<UserDTO> getAllUsers();
}
