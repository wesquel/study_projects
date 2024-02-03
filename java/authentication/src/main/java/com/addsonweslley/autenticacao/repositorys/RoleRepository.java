package com.addsonweslley.autenticacao.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addsonweslley.autenticacao.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
  Role findByName(String name);
}
