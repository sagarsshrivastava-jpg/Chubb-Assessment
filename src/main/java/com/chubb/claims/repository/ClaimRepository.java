package com.chubb.claims.repository;

import com.chubb.claims.entity.Claim;
import com.chubb.claims.common.enums.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimRepository
        extends JpaRepository<Claim, Long> {

    Optional<Claim> findByClaimNumber(
            String claimNumber);

    long countByStatus(
            ClaimStatus status);
}