package com.addsonweslley.autenticacao.dto.User;

import com.addsonweslley.autenticacao.models.App;
import com.addsonweslley.autenticacao.models.User;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserResponseFitApi(
        @NotBlank(message = "User id is needed!") Long authUserId,
        @NotBlank(message = "App id is needed!") Set<App> appsPermission
) {
    public static UserResponseFitApi fromUser(User user) {
        return new UserResponseFitApi(user.getId(), user.getAppAccess());
    }
}
