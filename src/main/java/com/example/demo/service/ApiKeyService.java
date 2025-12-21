package com.example.demo.service;

import com.example.demo.dto.ApiKeyDto;
import java.util.List;

public interface ApiKeyService {
    ApiKeyDto createApiKey(ApiKeyDto keyDto);
    ApiKeyDto updateApiKey(Long id, ApiKeyDto keyDto);
    ApiKeyDto getApiKeyById(Long id);
    ApiKeyDto getApiKeyByValue(String value);
    List<ApiKeyDto> getAllApiKeys();
    void deactivateApiKey(Long id);
}
