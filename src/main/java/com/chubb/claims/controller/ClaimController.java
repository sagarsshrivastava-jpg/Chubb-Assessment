package com.chubb.claims.controller;

import com.chubb.claims.dto.ClaimResponse;
import com.chubb.claims.dto.CreateClaimRequest;
import com.chubb.claims.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<ClaimResponse> createClaim(
            @Valid @RequestBody CreateClaimRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(claimService.createClaim(request));
    }

    @GetMapping("/{claimNumber}")
    public ResponseEntity<ClaimResponse> trackClaim(
            @PathVariable String claimNumber) {

        return ResponseEntity.ok(
                claimService.trackClaim(claimNumber)
        );
    }
}