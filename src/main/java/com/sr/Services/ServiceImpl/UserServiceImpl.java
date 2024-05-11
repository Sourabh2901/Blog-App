package com.sr.Services.ServiceImpl;


import com.sr.Entities.User;
import com.sr.Exception.ResourceNotFoundException;
import com.sr.Paylods.Dtos.UserDto;
import com.sr.Repository.UserRepo;
import com.sr.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
//        Fetch USer from Db
        User userToUpdate = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id : "+ id));

//        update required Details
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setPassword(userDto.getPassword());

//        Save the updated
        User UpdatedUser = userRepo.save(userToUpdate);
        return this.userToDto(UpdatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User fetchedUserById = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id : "+ id));

        return this.userToDto(fetchedUserById);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepo.findAll();
        List<UserDto> allUsers = userList.stream()
                                    .map(user -> this.userToDto(user))
                                    .collect(Collectors.toList());
        return allUsers;
    }

    @Override
    public void deleteUser(Integer id) {
        User fetchedUserById = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id : "+ id));

        userRepo.delete(fetchedUserById);
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
