package com.example.demo.controller;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.service.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
@Tag(name = "API Keys", description = "API Key Management")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping
    public ResponseEntity<ApiKeyDto> create(@RequestBody ApiKeyDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apiKeyService.createApiKey(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiKeyDto> update(@PathVariable Long id, @RequestBody ApiKeyDto dto) {
        return ResponseEntity.ok(apiKeyService.updateApiKey(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKeyDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(apiKeyService.getApiKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyDto>> getAll() {
        return ResponseEntity.ok(apiKeyService.getAllApiKeys());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        apiKeyService.deactivateApiKey(id);
        return ResponseEntity.noContent().build();
    }
}
