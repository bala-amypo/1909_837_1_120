package com.example.service.impl;

import com.example.dto.AuthRequestDto;
import com.example.dto.RegisterRequestDto;
import com.example.dto.AuthResponseDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;   // make sure configured

    @Override
    public String register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid Credentials");
        }

        // You can replace this with JWT token generation
        String token = "DUMMY_TOKEN";

        return new AuthResponseDto(user.getEmail(), token);
    }
}
