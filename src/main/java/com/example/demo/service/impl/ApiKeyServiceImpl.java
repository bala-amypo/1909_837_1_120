package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {
        // Generate a unique 32-char key value if not provided
        if (apiKey.getKeyValue() == null) {
            apiKey.setKeyValue(UUID.randomUUID().toString().replace("-", ""));
        }
        apiKey.setActive(true);
        return apiKeyRepository.save(apiKey);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey apiKey = getApiKeyById(id);
        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey apiKey) {
        ApiKey existing = getApiKeyById(id);
        existing.setActive(apiKey.getActive());
        existing.setPlan(apiKey.getPlan());
        return apiKeyRepository.save(existing);
    }
}