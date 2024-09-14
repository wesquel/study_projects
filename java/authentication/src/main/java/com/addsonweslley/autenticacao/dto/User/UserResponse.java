package com.addsonweslley.autenticacao.dto.User;

import com.addsonweslley.autenticacao.models.User;

public record UserResponse(String name, String username, String email) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getName(), user.getUsername(), user.getEmail());
    }

}
