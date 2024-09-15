package com.addsonweslley.autenticacao.controllers;

import com.addsonweslley.autenticacao.services.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/app")
public class AppController {

    @Autowired
    private AppService appService;

//    private ResponseEntity<?> listAllApps(){
//        return appService.listAll();
//    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable @Valid Long id){
        return appService.getAppById(id);
    }

}
