package com.sr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.entities.User;
import com.sr.execeptions.ResourceNotFoundException;
import com.sr.payloads.UserDto;
import com.sr.repositories.UserRepo;
import com.sr.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(User user) {
		User savedUser = userRepo.save(user);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId)
							.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		List<UserDto> allUsersDto = new ArrayList<>();
		
		for(User x:allUsers) {
			allUsersDto.add(this.userToDto(x));
		}
		
		return allUsersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "Id", userId));
		userRepo.delete(user);
	}
	
	
	private User dtoToUser(UserDto userDto) {
//		User user = new User(userDto.getId() ,userDto.getName() ,userDto.getEmail() ,userDto.getPassword() ,userDto.getAbout());
		
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto(user.getId() ,user.getName() ,user.getEmail() ,user.getPassword() ,user.getAbout());
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
