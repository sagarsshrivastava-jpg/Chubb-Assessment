package com.chubb.claims.repository;

import com.chubb.claims.entity.ClaimHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimHistoryRepository
        extends JpaRepository<ClaimHistory, Long> {
}