package com.example.controller;

import com.example.entity.ApiKey;
import com.example.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping
    public ApiKey createApiKey(@RequestBody ApiKey apiKey) {
        return apiKeyService.createApiKey(apiKey);
    }

    @PutMapping("/{id}")
    public ApiKey updateApiKey(@PathVariable Long id, @RequestBody ApiKey apiKey) {
        return apiKeyService.updateApiKey(id, apiKey);
    }

    @GetMapping("/{id}")
    public ApiKey getApiKeyById(@PathVariable Long id) {
        return apiKeyService.getApiKeyById(id);
    }

    @GetMapping
    public List<ApiKey> getAllKeys() {
        return apiKeyService.getAllApiKeys();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateKey(@PathVariable Long id) {
        apiKeyService.deactivateApiKey(id);
        return "API Key Deactivated Successfully";
    }
}
