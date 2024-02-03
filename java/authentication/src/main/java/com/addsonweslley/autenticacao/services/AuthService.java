package com.addsonweslley.autenticacao.services;

import com.addsonweslley.autenticacao.dto.LoginDto;

public interface AuthService {
  String login(LoginDto loginDto);
}
