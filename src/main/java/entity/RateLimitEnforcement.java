package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rate_limit_enforcement")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @NotNull
    @Column(nullable = false)
    private Timestamp blockedAt;

    @NotNull
    @Min(value = 1, message = "Limit exceeded must be at least 1")
    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(columnDefinition = "TEXT")
    private String message;
    

    public RateLimitEnforcement() {
    }
    

    public RateLimitEnforcement(Long id, ApiKey apiKey, Timestamp blockedAt, Integer limitExceededBy, String message) {
        this.id = id;
        this.apiKey = apiKey;
        this.blockedAt = blockedAt;
        this.limitExceededBy = limitExceededBy;
        this.message = message;
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

    public Timestamp getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(Timestamp blockedAt) {
        this.blockedAt = blockedAt;
    }

    public Integer getLimitExceededBy() {
        return limitExceededBy;
    }

    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
