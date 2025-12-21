package com.example.demo.security;


public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) { return null; }
}