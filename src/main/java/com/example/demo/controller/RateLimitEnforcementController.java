package com.example.demo.controller;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
@Tag(name = "Rate Limit Enforcement")
public class RateLimitEnforcementController {
    private final RateLimitEnforcementService enforcementService;

    public RateLimitEnforcementController(RateLimitEnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    @PostMapping
    public RateLimitEnforcement createEnforcement(@RequestBody RateLimitEnforcement enforcement) {
        return enforcementService.createEnforcement(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getEnforcementById(@PathVariable Long id) {
        return enforcementService.getEnforcementById(id);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getEnforcementsForKey(@PathVariable Long keyId) {
        return enforcementService.getEnforcementsForKey(keyId);
    }
}