package com.addsonweslley.autenticacao.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegister(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password,
        @NotBlank(message = "Name is required") String name,
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required") String email
    ) {
}
