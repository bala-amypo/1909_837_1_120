package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_account", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_quota_plan",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private Set<QuotaPlan> quotaPlans = new HashSet<>();

    public Long getId() {
        return id;
    }
    

    public UserAccount(Long id, String email, String password, String role, Set<QuotaPlan> quotaPlans) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.quotaPlans = quotaPlans;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<QuotaPlan> getQuotaPlans() {
        return quotaPlans;
    }

    public void setQuotaPlans(Set<QuotaPlan> quotaPlans) {
        this.quotaPlans = quotaPlans;
    }

    
    
}
