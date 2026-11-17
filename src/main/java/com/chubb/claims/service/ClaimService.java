package com.chubb.claims.service;

import com.chubb.claims.dto.ClaimResponse;
import com.chubb.claims.dto.CreateClaimRequest;
import com.chubb.claims.entity.Claim;
import com.chubb.claims.exception.ClaimNotFoundException;
import com.chubb.claims.repository.ClaimRepository;
import com.chubb.claims.common.enums.ClaimStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimAuditService auditService;

    public ClaimResponse createClaim(
            CreateClaimRequest request) {

        Claim claim =
                Claim.builder()
                        .claimNumber(
                                generateClaimNumber())
                        .policyNumber(
                                request.getPolicyNumber())
                        .claimType(
                                request.getClaimType())
                        .incidentDate(
                                request.getIncidentDate())
                        .estimatedAmount(
                                request.getEstimatedAmount())
                        .market(
                                request.getMarket())
                        .status(
                                ClaimStatus.SUBMITTED)
                        .createdAt(
                                LocalDateTime.now())
                        .updatedAt(
                                LocalDateTime.now())
                        .build();

        claimRepository.save(claim);

        auditService.auditStatusChange(
                claim,
                "",
                ClaimStatus.SUBMITTED.name(),
                "Claim submitted");

        return ClaimResponse.builder()
                .claimId(claim.getId())
                .claimNumber(
                        claim.getClaimNumber())
                .status(
                        claim.getStatus().name())
                .build();
    }

    public ClaimResponse trackClaim(
            String claimNumber) {

        Claim claim =
                claimRepository
                        .findByClaimNumber(
                                claimNumber)
                        .orElseThrow(() ->
                                new ClaimNotFoundException(
                                        claimNumber));

        return ClaimResponse.builder()
                .claimId(claim.getId())
                .claimNumber(
                        claim.getClaimNumber())
                .status(
                        claim.getStatus().name())
                .build();
    }

    public Claim findClaim(
            Long claimId) {

        return claimRepository
                .findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException(
                                claimId));
    }

    private String generateClaimNumber() {

        return "CHB-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}