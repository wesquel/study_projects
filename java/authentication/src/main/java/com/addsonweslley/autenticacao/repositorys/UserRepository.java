package com.addsonweslley.autenticacao.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addsonweslley.autenticacao.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findByUsernameOrEmail(String username, String email);
}
