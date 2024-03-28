package com.sr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sr.entities.User;
import com.sr.execeptions.ResourceNotFoundException;
import com.sr.repositories.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		finding user from database by username
		User user = userRepo.findByEmail(username)
			.orElseThrow(() -> new ResourceNotFoundException("User", " email :  " + username, 0));
		
		return user;
	}

}
