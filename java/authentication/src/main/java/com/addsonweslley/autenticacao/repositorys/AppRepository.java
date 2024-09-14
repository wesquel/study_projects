package com.addsonweslley.autenticacao.repositorys;

import com.addsonweslley.autenticacao.models.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface AppRepository extends JpaRepository<App, Long> {

}
