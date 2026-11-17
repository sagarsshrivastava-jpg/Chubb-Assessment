package com.chubb.claims.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimResponse {

    private Long claimId;

    private String claimNumber;

    private String status;
}