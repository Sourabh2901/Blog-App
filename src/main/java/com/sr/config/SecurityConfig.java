package com.sr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sr.security.JWTAuthenticationFilter;
import com.sr.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;


	// Configuring HttpSecurity
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		Disable Cors
		http.cors(corsConfig -> corsConfig.disable());
		
//		Disable csrf
		http.csrf(csrf -> csrf.disable());
		
//		Http Request Filter
		http.authorizeHttpRequests(
				requestMatcher -> requestMatcher.requestMatchers("/login").permitAll()
												.requestMatchers("/api/users/signUp").permitAll()
												.requestMatchers(HttpMethod.GET).permitAll()
												.anyRequest().authenticated()
				);
		
//		Authentication Entry Point
		http.exceptionHandling(
				exceptionConfig -> exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				);
				
//		Set Session Policy = stateless
		http.sessionManagement(
				sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);
		
//		Adding Jwt Auth filter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
//		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		
		var authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return authenticationProvider;
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
