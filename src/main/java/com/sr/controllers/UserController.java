package com.sr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.payloads.ApiResponse;
import com.sr.payloads.UserDto;
import com.sr.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser =  userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{userId}")
	private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable Integer userId) {
		UserDto updatedUser =  userService.updateUser(userDto ,userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	private ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User Deleted Succesfully" ,true), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	private ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	@GetMapping("/")
	private ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
}