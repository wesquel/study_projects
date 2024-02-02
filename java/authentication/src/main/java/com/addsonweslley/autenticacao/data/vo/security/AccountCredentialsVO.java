package com.addsonweslley.autenticacao.data.vo.security;

import java.io.Serializable;

public class AccountCredentialsVO implements Serializable{
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;

  public AccountCredentialsVO(String username, String password){
    this.username = username;
    this.password = password;
  }

}
