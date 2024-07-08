package com.addsonweslley.autenticacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.addsonweslley.autenticacao.security.jwt.JwtAuthenticationEntryPoint;
import com.addsonweslley.autenticacao.security.jwt.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  private JwtAuthenticationFilter authenticationFilter;

  @Bean
  static PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
    .authorizeHttpRequests(
      authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
      //.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
      .requestMatchers("/api/auth/**").permitAll()
      .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
      .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
      .anyRequest().authenticated()
    ).httpBasic(Customizer.withDefaults());

    http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
  }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails ramesh = User.builder()
//                .username("ramesh")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ramesh, admin);
//    }
}
