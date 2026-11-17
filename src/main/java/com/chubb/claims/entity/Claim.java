package com.chubb.claims.entity;

import com.chubb.claims.common.enums.ClaimStatus;
import com.chubb.claims.common.enums.ClaimType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Entity
@Table(appliesTo = "claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String claimNumber;

    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private ClaimType claimType;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private LocalDate incidentDate;

    private BigDecimal estimatedAmount;

    private String market;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}