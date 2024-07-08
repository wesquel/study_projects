package com.addsonweslley.autenticacao.controllers;

import com.addsonweslley.autenticacao.dto.User.UserRegister;
import com.addsonweslley.autenticacao.dto.User.UserResponse;
import com.addsonweslley.autenticacao.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegister userRegister) {
        try{
            UserResponse userResponse = userService.register(userRegister);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        }catch (ResponseStatusException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

}
