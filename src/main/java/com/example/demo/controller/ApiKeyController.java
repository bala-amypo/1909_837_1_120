package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
@Tag(name = "API Key Management")
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

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
    public List<ApiKey> getAllApiKeys() {
        return apiKeyService.getAllApiKeys();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateApiKey(@PathVariable Long id) {
        apiKeyService.deactivateApiKey(id);
    }
}