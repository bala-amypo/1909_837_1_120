package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final ModelMapper mapper;

    public AuthServiceImpl(UserAccountRepository userRepo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil,
                           ModelMapper mapper) {

        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        UserAccount user = mapper.map(request, UserAccount.class);
        user.setPassword(encoder.encode(request.getPassword()));

        UserAccount saved = userRepo.save(user);

        return mapper.map(saved, RegisterResponseDto.class);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        AuthResponseDto response = new AuthResponseDto();
        response.setEmail(user.getEmail());
        response.setUserId(user.getId());
        response.setRole(user.getRole());
        response.setToken(token);

        return response;
    }
}
