// package com.example.project.config;

// import static org.springframework.security.config.Customizer.withDefaults;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Bean
// SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
// return httpSecurity
// .authorizeHttpRequests(authorize -> authorize
// .requestMatchers("*").permitAll()
// .anyRequest().authenticated())
// .formLogin(withDefaults())
// .build();
// }
// }
