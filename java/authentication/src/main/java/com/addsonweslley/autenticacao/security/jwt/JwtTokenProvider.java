package com.addsonweslley.autenticacao.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;

	// Generate JWT Token

	public String generateToken(Authentication authentication){
		String username = authentication.getName();

		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder()
			.subject(username)
			.issuedAt(currentDate)
			.expiration(expireDate)
			.signWith(key())
			.compact();

		return token;
	}

	public Key key(){
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// Get username from JWT Token.
	public String getUsername(String token){
		return Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getSubject();
	}

	// validate JWT Token
	public boolean validateToken(String token){
		try {
			Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parse(token);
			return true;
		} catch (Exception ExpiredJwtException) {
			// TODO: handle exception
		}
	
		return false;
	}



}