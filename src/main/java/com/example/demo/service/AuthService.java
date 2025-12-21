package com.example.service;

import com.example.dto.AuthRequestDto;
import com.example.dto.RegisterRequestDto;
import com.example.dto.AuthResponseDto;

public interface AuthService {

    String register(RegisterRequestDto request);

    AuthResponseDto login(AuthRequestDto request);
}
