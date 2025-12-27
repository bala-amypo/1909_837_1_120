package com.example.demo.controller;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService service;

    public RateLimitEnforcementController(RateLimitEnforcementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RateLimitEnforcement> create(@RequestBody RateLimitEnforcement e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEnforcement(e));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateLimitEnforcement> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEnforcementById(id));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<RateLimitEnforcement>> key(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getEnforcementsForKey(keyId));
    }
}
