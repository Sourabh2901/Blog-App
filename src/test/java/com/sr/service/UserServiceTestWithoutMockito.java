package com.sr.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sr.payloads.UserDto;
import com.sr.services.impl.UserServiceImpl;


@SpringBootTest
public class UserServiceTestWithoutMockito {

//	@Autowired
//	private UserRepo userRepo;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	private RoleRepo roleRepo;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Test
	public void createUserTest() {
		
		UserDto userDto = UserDto.builder().name("16Apr").about("Java Developer")
				.email("16Apr@gmail.com").password("sourabh").build();
		
		UserDto savedUser = userService.createUser(userDto);
		
		Assertions.assertThat(savedUser).isNotNull();
	}
}
