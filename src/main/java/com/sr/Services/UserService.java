package com.sr.Services;

import com.sr.Paylods.Dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto user ,Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUser();

    void deleteUser(Integer userId);
}
