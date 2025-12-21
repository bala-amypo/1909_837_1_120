package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
@Tag(name = "Key Exemptions")
public class KeyExemptionController {
    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping
    public KeyExemption createExemption(@RequestBody KeyExemption exemption) {
        return exemptionService.createExemption(exemption);
    }

    @GetMapping("/key/{keyId}")
    public KeyExemption getByApiKey(@PathVariable Long keyId) {
        return exemptionService.getExemptionByKey(keyId).orElse(null);
    }

    @GetMapping
    public List<KeyExemption> getAll() {
        return exemptionService.getAllExemptions();
    }
}