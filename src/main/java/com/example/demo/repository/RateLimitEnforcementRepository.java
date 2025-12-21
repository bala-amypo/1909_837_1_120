package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RateLimitEnforcementRepository extends JpaRepository<RateLimitEnforcement, Long> {
    
    /**
     * Extra method: Find all enforcement records for a specific API Key. [cite: 146]
     */
    List<RateLimitEnforcement> findByApiKey_Id(Long id);
}