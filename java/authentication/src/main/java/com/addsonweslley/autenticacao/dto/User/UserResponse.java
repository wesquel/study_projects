package com.addsonweslley.autenticacao.dto.User;

import com.addsonweslley.autenticacao.models.App;
import com.addsonweslley.autenticacao.models.Role;
import com.addsonweslley.autenticacao.models.User;

import java.util.Set;

public record UserResponse(String name, String username, String email, Set<Role> roles, Set<App> app_access) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getName(), user.getUsername(), user.getEmail(), user.getRoles(), user.getAppAccess());
    }

}
