package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="api_key", uniqueConstraints = @UniqueConstraint(columnNames="key_value"))
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="key_value", nullable=false, unique=true)
    private String keyValue;

    @Column(nullable=false)
    private Long ownerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="plan_id", nullable=false)
    private QuotaPlan plan;

    @Column(nullable=false)
    private Boolean active = true;

    @Column(nullable=false)
    private Timestamp createdAt;

    @Column(nullable=false)
    private Timestamp updatedAt;

    // getters + setters
}
