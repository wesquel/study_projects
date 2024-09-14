package com.addsonweslley.autenticacao.services;

import com.addsonweslley.autenticacao.dto.App.AppResponse;
import com.addsonweslley.autenticacao.dto.User.UserRequest;
import com.addsonweslley.autenticacao.models.App;
import com.addsonweslley.autenticacao.repositorys.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public ResponseEntity<?> listAll(Pageable pageable){

        if (pageable == null) {
            return ResponseEntity.badRequest().body("Erro de paginação!");
        }

        Page<App> appPage = appRepository.findAll(pageable);

        return ResponseEntity.ok(appPage);
    }

    public ResponseEntity<?> getAppById(Long id){

        Optional<App> app = appRepository.findById(id);

        if (app.isEmpty()) {
            return ResponseEntity.badRequest().body("Error ao encontrar a aplicação");
        }

        App appPersistence = app.get();

        AppResponse appResponse = new AppResponse(appPersistence.getId(), appPersistence.getAppName());

        return ResponseEntity.ok(appResponse);
    }


    public void userApps(UserRequest userRequest){


    }

}
