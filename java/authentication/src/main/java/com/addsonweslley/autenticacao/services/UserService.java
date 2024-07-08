package com.addsonweslley.autenticacao.services;

import com.addsonweslley.autenticacao.dto.User.UserRegister;
import com.addsonweslley.autenticacao.dto.User.UserResponse;
import com.addsonweslley.autenticacao.models.Role;
import com.addsonweslley.autenticacao.models.User;
import com.addsonweslley.autenticacao.repositorys.RoleRepository;
import com.addsonweslley.autenticacao.repositorys.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;

@Service
public class UserService {

    public final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse register(UserRegister userRegister) {

        if (this.checkUserExists(userRegister.username(), userRegister.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Role role = this.getRole("USER");

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User(
                userRegister.name(),
                userRegister.username(),
                userRegister.email(),
                bCryptPasswordEncoder.encode(userRegister.password()),
                roles
        );

        User userSave = userRepository.save(user);
        return UserResponse.fromUser(userSave);
    }

    private Role getRole(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    public Boolean checkUserExists(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).isPresent();
    }

}
