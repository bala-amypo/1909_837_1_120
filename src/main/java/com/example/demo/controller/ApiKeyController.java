package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    private final ApiKeyService service;

    public ApiKeyController(ApiKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiKey> create(@RequestBody ApiKey dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createApiKey(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiKey> update(@PathVariable Long id, @RequestBody ApiKey dto) {
        return ResponseEntity.ok(service.updateApiKey(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKey> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApiKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApiKey>> getAll() {
        return ResponseEntity.ok(service.getAllApiKeys());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateApiKey(id);
        return ResponseEntity.noContent().build();
    }
}
