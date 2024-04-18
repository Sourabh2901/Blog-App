package com.sr.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sr.entities.Role;
import com.sr.entities.User;
import com.sr.payloads.RoleDto;
import com.sr.payloads.UserDto;
import com.sr.repositories.RoleRepo;
import com.sr.repositories.UserRepo;
import com.sr.services.impl.UserServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
public class userServiceTest {
	
	@Mock
	private UserRepo userRepo;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private RoleRepo roleRepo;
	
	private User user;
	private UserDto userDto;
	private Role role;
	private RoleDto roleDto;
	
	@BeforeEach
	public void init() {
		
		role = Role.builder().name("USER").id(1).build();
		roleDto = RoleDto.builder().id(1).name("USER").build();
		
		user = User.builder().name("Sourabh").about("Java Developer")
				.email("sourabh@gmail.com").password("sourabh").roles(Set.of(role)).build();
		
		userDto = UserDto.builder().name("Sourabh").about("Java Developer")
				.email("sourabh@gmail.com").password("sourabh").roles(Set.of(roleDto)).build();
	}
	
//	@Test
	public void test_createUser() {
//		Arrange
		Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		Mockito.when(modelMapper.map(userDto, User.class)).thenReturn(user);
		Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
		Mockito.when(roleRepo.findById(1)).thenReturn(Optional.of(role));

//		Act
		UserDto savedUser = userServiceImpl.createUser(userDto);
		
//		Assert
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getName()).isEqualTo(user.getName());
	}
	
	
	@Test
	public void test_updateUser() {
//		Arrange
		Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
		Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);	
				
//		Act
		UserDto updatedUser = userServiceImpl.updateUser(userDto ,user.getId());
		
//		Assert
		Assertions.assertThat(updatedUser).isNotNull();
		Assertions.assertThat(updatedUser.getName()).isEqualTo(user.getName());
	}
	
//	@Test
	public void test_getUserById() {
//		Arrange
		when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(modelMapper.map(Mockito.any(User.class), Mockito.any())).thenReturn(userDto);
				
//		Act
		UserDto getUser = userServiceImpl.getUserById(user.getId());
		
//		Assert
		Assertions.assertThat(getUser).isNotNull();
		Assertions.assertThat(getUser.getName()).isEqualTo(user.getName());
	}
	
	@Test
	public void test_getAllUsers() {
//		Arrange
		when(userRepo.findAll()).thenReturn(Arrays.asList(user));
        Mockito.when(modelMapper.map(Mockito.any(User.class), Mockito.any())).thenReturn(userDto);
				
//		Act
        List<UserDto> allUser = userServiceImpl.getAllUsers();
		
//		Assert
		Assertions.assertThat(allUser).isNotNull();
		Assertions.assertThat(allUser.get(0).getName()).isEqualTo(user.getName());
	}
	
	
//	@Test
	public void test_deleteUser() {
//		Arrange
		when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
				
//		Act
        userServiceImpl.deleteUser(user.getId());
       
		
//		Assert
		verify(userRepo, times(1)).findById(user.getId());
		verify(userRepo, times(1)).delete(user);
	}
	
	
}
