package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {
    List<ApiUsageLog> findByApiKey_Id(Long apiKeyId);
    List<ApiUsageLog> findByApiKey_IdAndTimestampAfter(Long apiKeyId, LocalDateTime timestamp);
}