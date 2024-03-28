package com.sr.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		Fetching token from request
		String authHeader = request.getHeader("Authorization");
		String jwtToken = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			jwtToken = authHeader.substring(7);
		}
		
//		Get UserName From JwtToken
		String userName = jwtTokenHelper.getUserNameFromToken(jwtToken);
		
//		Validate JwtToken
		if(jwtToken != null && userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			if(jwtTokenHelper.validateToken(jwtToken)) {
				
//				Fetch user Details with the help of userName 
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				
//				create Authentication Token
				var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
//				Set Authentication token to Security Context
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}else {
				System.out.println("Jwt Token is Invalid !!");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
}
