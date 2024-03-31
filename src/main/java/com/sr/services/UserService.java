package com.sr.services;

import java.util.List;

import com.sr.entities.User;
import com.sr.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(User user);
	UserDto updateUser(UserDto user ,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
