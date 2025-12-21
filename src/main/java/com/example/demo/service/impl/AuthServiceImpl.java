package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount; // Fix: import from .entity
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        // Fix: Call generateToken with UserDetails object only
        String token = jwtUtil.generateToken(userDetails); 
        return new AuthResponseDto(token, 1L, request.getEmail(), "USER");
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        // Implementation logic
        return new AuthResponseDto("temp-token", 1L, request.getEmail(), request.getRole());
    }
}