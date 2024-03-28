package com.sr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sr.payloads.JwtAuthRequest;
import com.sr.payloads.JwtAuthResponse;
import com.sr.security.JwtTokenHelper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authRequest) throws Exception{
		
		this.authenticate(authRequest.getUsername() ,authRequest.getPassword());
		
		UserDetails userObj = userDetailsService.loadUserByUsername(authRequest.getUsername());
		
		String jwtToken = jwtTokenHelper.generateToken(userObj.getUsername());
		
		JwtAuthResponse obj = new JwtAuthResponse(jwtToken , HttpStatus.OK);
		
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new Exception("Invalid Username or Password !! ");
		}
	}
	
	
}
