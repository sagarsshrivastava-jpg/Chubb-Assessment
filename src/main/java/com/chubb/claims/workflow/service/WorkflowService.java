package com.chubb.claims.workflow.service;

import com.chubb.claims.entity.Claim;
import com.chubb.claims.repository.ClaimRepository;
import com.chubb.claims.service.ClaimAuditService;
import com.chubb.claims.common.enums.ClaimStatus;
import com.chubb.claims.workflow.dto.AssignClaimRequest;
import com.chubb.claims.workflow.dto.DecisionRequest;
import com.chubb.claims.workflow.dto.InfoRequest;
import com.chubb.claims.workflow.entity.Task;
import com.chubb.claims.workflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkflowService {

    private final ClaimRepository claimRepository;
    private final TaskRepository taskRepository;
    private final ClaimAuditService auditService;

    public String assignClaim(
            Long claimId,
            AssignClaimRequest request) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow();

        String oldStatus =
                claim.getStatus().name();

        claim.setStatus(ClaimStatus.ASSIGNED);

        Task task =
                Task.builder()
                        .claimId(claimId)
                        .assignedUserId(
                                request.getUserId())
                        .assignedUserName(
                                request.getUserName())
                        .assignedAt(
                                LocalDateTime.now())
                        .completed(false)
                        .build();

        taskRepository.save(task);

        auditService.auditStatusChange(
                claim,
                oldStatus,
                ClaimStatus.ASSIGNED.name(),
                "Claim assigned");

        return "Claim assigned successfully";
    }

    public String requestInfo(
            Long claimId,
            InfoRequest request) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow();

        String oldStatus =
                claim.getStatus().name();

        claim.setStatus(
                ClaimStatus.AWAITING_INFO);

        auditService.auditStatusChange(
                claim,
                oldStatus,
                ClaimStatus.AWAITING_INFO.name(),
                request.getRemarks());

        return "Additional information requested";
    }

    public String approveClaim(
            Long claimId,
            DecisionRequest request) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow();

        String oldStatus =
                claim.getStatus().name();

        claim.setStatus(
                ClaimStatus.APPROVED);

        auditService.auditStatusChange(
                claim,
                oldStatus,
                ClaimStatus.APPROVED.name(),
                request.getRemarks());

        return "Claim approved";
    }

    public String rejectClaim(
            Long claimId,
            DecisionRequest request) {

        Claim claim =
                claimRepository.findById(claimId)
                        .orElseThrow();

        String oldStatus =
                claim.getStatus().name();

        claim.setStatus(
                ClaimStatus.REJECTED);

        auditService.auditStatusChange(
                claim,
                oldStatus,
                ClaimStatus.REJECTED.name(),
                request.getRemarks());

        return "Claim rejected";
    }
}