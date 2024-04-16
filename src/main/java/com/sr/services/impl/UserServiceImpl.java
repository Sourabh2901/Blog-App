package com.sr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sr.config.AppConstant;
import com.sr.entities.Role;
import com.sr.entities.User;
import com.sr.execeptions.ResourceNotFoundException;
import com.sr.execeptions.UserCreationException;
import com.sr.payloads.UserDto;
import com.sr.repositories.RoleRepo;
import com.sr.repositories.UserRepo;
import com.sr.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	Instead of declaring this again and again use Annotation at top of class named @Slf4j
//	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDto createUser(UserDto userDto) {
		
		try {
			User user = this.modelMapper.map(userDto, User.class);
			
//			Encoding password
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			
//			Setting roles
			Role role = roleRepo.findById(AppConstant.NORMAL_USER).get();
			user.getRoles().add(role);
			
//			Saving user in DB
			User savedUser = userRepo.save(user);
			
			log.info("User Saved Succesfully : "+user.getName());
			
			return this.modelMapper.map(savedUser, UserDto.class);
		}catch(Exception ex) {
			log.error("Exception Occured While Saving User in DB : "+ ex.getMessage());
//			log.debug("Debug logger While Saving User in DB : "+ ex.getMessage());
			throw new UserCreationException("Exception Occured While Saving User in DB");
		}
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
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId)
							.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		List<UserDto> allUsersDto = new ArrayList<>();
		
		for(User x:allUsers) {
			allUsersDto.add(this.modelMapper.map(x, UserDto.class));
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
