package com.example.service;

import com.example.entity.ApiKey;
import java.util.List;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey key);

    ApiKey updateApiKey(Long id, ApiKey updatedKey);

    ApiKey getApiKeyById(Long id);

    ApiKey getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
