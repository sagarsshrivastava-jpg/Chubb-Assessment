package com.chubb.claims.exception;

public class ClaimNotFoundException
        extends RuntimeException {

    public ClaimNotFoundException(String claimNumber) {
        super("Claim not found : " + claimNumber);
    }

    public ClaimNotFoundException(Long id) {
        super("Claim not found : " + id);
    }
}