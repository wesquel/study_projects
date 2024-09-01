package com.addsonweslley.autenticacao.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;

import com.addsonweslley.autenticacao.dto.JwtAuthResponse;
import com.addsonweslley.autenticacao.dto.LoginDto;
import com.addsonweslley.autenticacao.services.AuthServiceImpl;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController  {
  private AuthServiceImpl authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

    String token = authService.login(loginDto);
    String error_message = " “User not found or invalid password!";

    if (token == null){
      return new ResponseEntity<>(error_message, HttpStatus.UNAUTHORIZED);
    }

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);

    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

  }

}
