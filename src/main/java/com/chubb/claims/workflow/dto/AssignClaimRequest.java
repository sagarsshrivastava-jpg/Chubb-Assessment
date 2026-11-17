package com.chubb.claims.workflow.dto;

import lombok.Data;

@Data
public class AssignClaimRequest {

    private Long userId;

    private String userName;
}