package com.example.demo.dto;

public class AuthRequestDto {
    private String email;
    private String password;

    // No-arg constructor
    public AuthRequestDto() {}

    // Parameterized constructor
    public AuthRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}