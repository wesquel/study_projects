package com.addsonweslley.autenticacao.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;
import com.addsonweslley.autenticacao.dto.LoginDto;
import com.addsonweslley.autenticacao.security.jwt.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private AuthenticationManager authenticationManager;
  private JwtTokenProvider jwtTokenProvider;

  @Override
  public String login(LoginDto loginDto) {
    String token = null;
    try {

      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(),
            loginDto.getPassword()
            
      ));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtTokenProvider.generateToken(authentication);

    } catch (BadCredentialsException e) {
      // TODO LOG
    }

    return token;
  }
}
