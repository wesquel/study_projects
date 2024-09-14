package com.addsonweslley.autenticacao.config;

import com.addsonweslley.autenticacao.models.App;
import com.addsonweslley.autenticacao.models.Role;
import com.addsonweslley.autenticacao.repositorys.AppRepository;
import com.addsonweslley.autenticacao.repositorys.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRolesDatabase(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role("USER"));
                roleRepository.save(new Role("ADMIN"));
                roleRepository.save(new Role("TEACHER"));
            }
        };
    }

    @Bean
    CommandLineRunner initAppsDatabase(AppRepository appRepository) {
        return args -> {
            if (appRepository.count() == 0) {
                appRepository.save(new App("FitStatus"));
            }
        };
    }
}
