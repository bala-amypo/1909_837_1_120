package com.example.controller;

import com.example.entity.RateLimitEnforcement;
import com.example.service.RateLimitEnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    @Autowired
    private RateLimitEnforcementService service;

    // POST /  -> Create enforcement event
    @PostMapping
    public RateLimitEnforcement createEnforcement(@RequestBody RateLimitEnforcement enforcement) {
        return service.createEnforcement(enforcement);
    }

    // GET /{id} -> Get enforcement by ID
    @GetMapping("/{id}")
    public RateLimitEnforcement getEnforcementById(@PathVariable Long id) {
        return service.getEnforcementById(id);
    }

    // GET /key/{keyId} -> Get enforcement events for API key
    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getEnforcementsForKey(@PathVariable Long keyId) {
        return service.getEnforcementsForKey(keyId);
    }
}
