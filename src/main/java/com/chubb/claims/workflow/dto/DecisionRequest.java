package com.chubb.claims.workflow.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DecisionRequest {

    private String remarks;

    private BigDecimal approvedAmount;
}