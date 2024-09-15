package com.addsonweslley.autenticacao.services;

import com.addsonweslley.autenticacao.dto.User.UserRegister;
import com.addsonweslley.autenticacao.dto.User.UserResponse;
import com.addsonweslley.autenticacao.models.App;
import com.addsonweslley.autenticacao.models.Role;
import com.addsonweslley.autenticacao.models.User;
import com.addsonweslley.autenticacao.repositorys.AppRepository;
import com.addsonweslley.autenticacao.repositorys.RoleRepository;
import com.addsonweslley.autenticacao.repositorys.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AppRepository appRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AppRepository appRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.appRepository = appRepository;
    }

    public UserResponse register(UserRegister userRegister) {

        if (this.checkUserExists(userRegister.username(), userRegister.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Role role = this.getRole("ROLE_USER");

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        App app = this.getApp(1L);

        HashSet<App> appsAcess = new HashSet<>();
        appsAcess.add(app);


        User user = new User(
                userRegister.name(),
                userRegister.username(),
                userRegister.email(),
                bCryptPasswordEncoder.encode(userRegister.password()),
                roles,
                appsAcess
        );

        User userSave = userRepository.save(user);
        return UserResponse.fromUser(userSave);
    }

    private Role getRole(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    private App getApp(Long appId) {
        return this.appRepository.findById(appId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "App not found")
        );
    }

    public Boolean checkUserExists(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).isPresent();
    }

}
