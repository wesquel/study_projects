package com.addsonweslley.autenticacao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addsonweslley.autenticacao.dto.JwtAuthResponse;
import com.addsonweslley.autenticacao.dto.LoginDto;
import com.addsonweslley.autenticacao.services.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController  {
  private AuthService authService;

  // Build Login REST API
  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
    String token = authService.login(loginDto);

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);
      
    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
  }  
}
