package com.example.demo.model;

@Document(collection = "users")
public class AppUser {
    @Id
    private String id;
    private String username;
    private String password; // Plain text (not recommended for prod!)
    private List<String> roles;
    
    // Getters and setters
}

