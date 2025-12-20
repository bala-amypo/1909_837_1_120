// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "api_key", uniqueConstraints = @UniqueConstraint(columnNames = "key_value"))
// public class ApiKey {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank
//     @Column(name = "key_value", nullable = false, unique = true)
//     private String keyValue;

//     @NotNull
//     @Column(nullable = false)
//     private Long ownerId;

//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "plan_id", nullable = false)
//     private QuotaPlan plan;

//     @Column(nullable = false)
//     private Boolean active = true;

//     @Column(nullable = false, updatable = false)
//     private Timestamp createdAt;

//     @Column(nullable = false)
//     private Timestamp updatedAt;
    
    
//     public ApiKey() {
//     }
    

//     public ApiKey(Long id, String keyValue, Long ownerId, QuotaPlan plan, Boolean active, Timestamp createdAt,
//             Timestamp updatedAt) {
//         this.id = id;
//         this.keyValue = keyValue;
//         this.ownerId = ownerId;
//         this.plan = plan;
//         this.active = active;
//         this.createdAt = createdAt;
//         this.updatedAt = updatedAt;
//     }


//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getKeyValue() {
//         return keyValue;
//     }

//     public void setKeyValue(String keyValue) {
//         this.keyValue = keyValue;
//     }

//     public Long getOwnerId() {
//         return ownerId;
//     }

//     public void setOwnerId(Long ownerId) {
//         this.ownerId = ownerId;
//     }

//     public QuotaPlan getPlan() {
//         return plan;
//     }

//     public void setPlan(QuotaPlan plan) {
//         this.plan = plan;
//     }

//     public Boolean getActive() {
//         return active;
//     }

//     public void setActive(Boolean active) {
//         this.active = active;
//     }

//     public Timestamp getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(Timestamp createdAt) {
//         this.createdAt = createdAt;
//     }

//     public Timestamp getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setUpdatedAt(Timestamp updatedAt) {
//         this.updatedAt = updatedAt;
//     }

      
// }
