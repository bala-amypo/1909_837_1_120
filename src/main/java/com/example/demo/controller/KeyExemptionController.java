package com.example.demo.controller;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.service.KeyExemptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
@Tag(name = "Key Exemptions", description = "API Key Exemption Management")
public class KeyExemptionController {

    private final KeyExemptionService service;

    public KeyExemptionController(KeyExemptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<KeyExemptionDto> create(@RequestBody KeyExemptionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createExemption(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyExemptionDto> update(@PathVariable Long id,
                                                  @RequestBody KeyExemptionDto dto) {
        return ResponseEntity.ok(service.updateExemption(id, dto));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<KeyExemptionDto> getByKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getExemptionByKey(keyId));
    }

    @GetMapping
    public ResponseEntity<List<KeyExemptionDto>> getAll() {
        return ResponseEntity.ok(service.getAllExemptions());
    }
}
