// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Min;

// @Entity
// @Table(name = "quota_plan", uniqueConstraints = @UniqueConstraint(columnNames = "plan_name"))
// public class QuotaPlan {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name="plan_name", nullable = false, unique = true)
//     private String planName;

//     @Column(nullable = false)
//     @Min(1)
//     private Integer dailyLimit;

//     private String description;

//     @Column(nullable = false)
//     private Boolean active = true;

//     // getters & setters
// }
