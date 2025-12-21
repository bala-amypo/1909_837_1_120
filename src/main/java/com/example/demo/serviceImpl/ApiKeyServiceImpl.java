package com.example.service.impl;

import com.example.entity.ApiKey;
import com.example.repository.ApiKeyRepository;
import com.example.repository.QuotaPlanRepository;
import com.example.service.ApiKeyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private QuotaPlanRepository quotaPlanRepository;

    @Override
    public ApiKey createApiKey(ApiKey key) {
        var plan = quotaPlanRepository.findById(key.getQuotaPlan().getId())
                .orElseThrow(() -> new EntityNotFoundException("Quota Plan Not Found"));

        if (!plan.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot assign inactive quota plan");
        }

        key.setActive(true);
        return apiKeyRepository.save(key);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey updatedKey) {
        ApiKey existing = getApiKeyById(id);
        existing.setKeyValue(updatedKey.getKeyValue());
        existing.setActive(updatedKey.isActive());
        existing.setQuotaPlan(updatedKey.getQuotaPlan());
        return apiKeyRepository.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("API Key Not Found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String keyValue) {
        return apiKeyRepository.findByKeyValue(keyValue)
                .orElseThrow(() -> new EntityNotFoundException("API Key Not Found"));
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        apiKeyRepository.save(key);
    }
}
