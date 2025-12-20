package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "key_exemption")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

    @Min(value = 0, message = "Temporary extension must be >= 0")
    private Integer temporaryExtensionLimit;

    @NotNull
    @Column(nullable = false)
    private Timestamp validUntil;

    
    public KeyExemption() {
    }
    

    public KeyExemption(Long id, ApiKey apiKey, String notes, Boolean unlimitedAccess, Integer temporaryExtensionLimit,
            Timestamp validUntil) {
        this.id = id;
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getUnlimitedAccess() {
        return unlimitedAccess;
    }

    public void setUnlimitedAccess(Boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }

    
}
