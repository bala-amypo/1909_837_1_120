package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthServiceImpl(UserAccountRepository repo, PasswordEncoder encoder, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @Override
    public void register(RegisterRequestDto dto) {
        if(repo.existsByEmail(dto.getEmail()))
            throw new BadRequestException("Email already exists");

        UserAccount u = new UserAccount();
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRole(dto.getRole());
        repo.save(u);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwt.generateToken(null, user.getEmail());
        AuthResponseDto res = new AuthResponseDto();
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setToken(token);
        return res;
    }
}
