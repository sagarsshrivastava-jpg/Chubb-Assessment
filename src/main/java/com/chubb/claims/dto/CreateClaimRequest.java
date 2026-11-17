package com.chubb.claims.dto;

import lombok.Data;

import com.chubb.claims.common.enums.ClaimType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateClaimRequest {

    @NotBlank
    private String policyNumber;

    @NotNull
    private ClaimType claimType;

    @NotNull
    private LocalDate incidentDate;

    @NotNull
    private BigDecimal estimatedAmount;

    @NotBlank
    private String market;

    @NotBlank
    private String claimantName;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;
}