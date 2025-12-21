package com.example.controller;

import com.example.entity.KeyExemption;
import com.example.service.KeyExemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    @Autowired
    private KeyExemptionService service;

    // POST /  -> Create exemption
    @PostMapping
    public KeyExemption createExemption(@RequestBody KeyExemption exemption) {
        return service.createExemption(exemption);
    }

    // PUT /{id} -> Update exemption
    @PutMapping("/{id}")
    public KeyExemption updateExemption(@PathVariable Long id,
                                        @RequestBody KeyExemption exemption) {
        return service.updateExemption(id, exemption);
    }

    // GET /key/{keyId} -> Get exemption info for API key
    @GetMapping("/key/{keyId}")
    public KeyExemption getExemptionByKey(@PathVariable Long keyId) {
        return service.getExemptionByKey(keyId);
    }

    // GET / -> List all exemptions
    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return service.getAllExemptions();
    }
}
