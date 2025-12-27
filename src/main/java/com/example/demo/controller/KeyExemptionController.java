package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService service;

    public KeyExemptionController(KeyExemptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<KeyExemption> create(@RequestBody KeyExemption e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createExemption(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyExemption> update(@PathVariable Long id, @RequestBody KeyExemption e) {
        return ResponseEntity.ok(service.updateExemption(id, e));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<KeyExemption> get(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getExemptionByKey(keyId));
    }

    @GetMapping
    public ResponseEntity<List<KeyExemption>> all() {
        return ResponseEntity.ok(service.getAllExemptions());
    }
}
