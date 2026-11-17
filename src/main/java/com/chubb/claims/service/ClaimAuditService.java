package com.chubb.claims.service;

import com.chubb.claims.entity.Claim;
import com.chubb.claims.entity.ClaimHistory;
import com.chubb.claims.repository.ClaimHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClaimAuditService {

    private final ClaimHistoryRepository
            historyRepository;

    public void auditStatusChange(
            Claim claim,
            String oldStatus,
            String newStatus,
            String remarks) {

        ClaimHistory history =
                ClaimHistory.builder()
                        .claimId(claim.getId())
                        .oldStatus(oldStatus)
                        .newStatus(newStatus)
                        .remarks(remarks)
                        .changedAt(
                                LocalDateTime.now())
                        .build();

        historyRepository.save(history);
    }
}