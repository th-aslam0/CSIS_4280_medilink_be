package com.example.medilinkbe.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, " + jwt.getClaim("email") + "! This is a protected endpoint.";
    }
}
