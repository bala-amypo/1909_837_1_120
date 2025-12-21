package com.example.demo.controller;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.service.RateLimitEnforcementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
@Tag(name = "Rate Limit Enforcement", description = "Enforcement Logs")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService service;

    public RateLimitEnforcementController(RateLimitEnforcementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RateLimitEnforcementDto> create(@RequestBody RateLimitEnforcementDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEnforcement(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateLimitEnforcementDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEnforcementById(id));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<RateLimitEnforcementDto>> getForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getEnforcementsForKey(keyId));
    }
}
