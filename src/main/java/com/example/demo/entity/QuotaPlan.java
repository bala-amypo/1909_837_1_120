package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quota_plans")
public class QuotaPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String planName;

    private Integer dailyLimit;
    private String description;
    private Boolean active = true;

    public QuotaPlan() {}

    public QuotaPlan(String planName, Integer dailyLimit, String description, Boolean active) {
        this.planName = planName;
        this.dailyLimit = dailyLimit;
        this.description = description;
        this.active = active != null ? active : true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getPlanName() { return planName; }
    public Integer getDailyLimit() { return dailyLimit; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public void setDailyLimit(Integer dailyLimit) { this.dailyLimit = dailyLimit; }
    public void setPlanName(String planName) { this.planName = planName; }
}