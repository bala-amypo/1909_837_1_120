package com.example.demo.controller;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.service.ApiKeyService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    private final ApiKeyService service;

    public ApiKeyController(ApiKeyService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiKeyDto> create(@RequestBody ApiKeyDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createApiKey(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiKeyDto> update(@PathVariable Long id, @RequestBody ApiKeyDto dto){
        return ResponseEntity.ok(service.updateApiKey(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKeyDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getApiKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyDto>> getAll(){
        return ResponseEntity.ok(service.getAllApiKeys());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id){
        service.deactivateApiKey(id);
        return ResponseEntity.noContent().build();
    }
}
