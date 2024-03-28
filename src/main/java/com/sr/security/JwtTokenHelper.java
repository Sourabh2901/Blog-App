package com.sr.security;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenHelper {
	
	private static final SecretKey secretKey = (SecretKey) Jwts.SIG.HS256.key().build();
	
	private static final String ISSUER = "Sourabh JWT";

	public String getUserNameFromToken(String jwtToken) {
		var claims = parseToken(jwtToken);
		if(claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}
	
	public String generateToken(String username) {
		var currentDate = new Date();
		var JwtExpiration = DateUtils.addMinutes(currentDate, 10);
		
		return Jwts.builder()
			.id(UUID.randomUUID().toString())
			.issuer(ISSUER)
			.subject(username)
			.signWith(secretKey)
			.issuedAt(currentDate)
			.expiration(JwtExpiration)
			.compact();
		
	}

	public boolean validateToken(String jwtToken) {
		return parseToken(jwtToken) != null;
	}

	private Claims parseToken(String jwtToken) {
		var jwtParser = Jwts.parser()
			.verifyWith(secretKey)
			.build();
		
		try {
			return jwtParser
					.parseSignedClaims(jwtToken)
					.getPayload();
		}catch(Exception e) {
			log.error("Jwt Exception Occured");
		}
		
		return null;
	}

	
}
