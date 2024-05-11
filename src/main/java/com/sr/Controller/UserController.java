package com.sr.Controller;

import com.sr.Paylods.ApiResponse;
import com.sr.Paylods.Dtos.UserDto;
import com.sr.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUserDto =  userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Integer userId) {
        UserDto updatedUser =  userService.updateUser(userDto ,userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

//    @Hidden
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

    @GetMapping
    private ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userList = userService.getAllUser();
        return new ResponseEntity(userList, HttpStatus.OK);
    }
}
