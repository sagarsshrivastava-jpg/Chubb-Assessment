package com.chubb.claims.workflow;

import com.chubb.claims.workflow.dto.AssignClaimRequest;
import com.chubb.claims.workflow.dto.DecisionRequest;
import com.chubb.claims.workflow.dto.InfoRequest;
import com.chubb.claims.workflow.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;

    @PostMapping("/{claimId}/assign")
    public ResponseEntity<String> assignClaim(
            @PathVariable Long claimId,
            @RequestBody AssignClaimRequest request) {

        return ResponseEntity.ok(
                workflowService.assignClaim(
                        claimId,
                        request));
    }

    @PostMapping("/{claimId}/request-info")
    public ResponseEntity<String> requestInfo(
            @PathVariable Long claimId,
            @RequestBody InfoRequest request) {

        return ResponseEntity.ok(
                workflowService.requestInfo(
                        claimId,
                        request));
    }

    @PostMapping("/{claimId}/approve")
    public ResponseEntity<String> approveClaim(
            @PathVariable Long claimId,
            @RequestBody DecisionRequest request) {

        return ResponseEntity.ok(
                workflowService.approveClaim(
                        claimId,
                        request));
    }

    @PostMapping("/{claimId}/reject")
    public ResponseEntity<String> rejectClaim(
            @PathVariable Long claimId,
            @RequestBody DecisionRequest request) {

        return ResponseEntity.ok(
                workflowService.rejectClaim(
                        claimId,
                        request));
    }
}