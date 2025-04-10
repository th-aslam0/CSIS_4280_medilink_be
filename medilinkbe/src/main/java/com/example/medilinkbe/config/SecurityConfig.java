package com.example.medilinkbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http
    //         .csrf().disable()
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/public/**").permitAll()
    //             .anyRequest()
    //             .authenticated()
    //         )
    //         .oauth2Login()
    //         .and()
    //         .oauth2ResourceServer(oauth2 -> oauth2.jwt())
    //         .build();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests without authentication
            )
            .build();
    }
}
