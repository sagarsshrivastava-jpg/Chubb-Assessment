package com.chubb.claims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ClaimNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleClaimNotFound(
            ClaimNotFoundException ex) {

        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .message(
                                        ex.getMessage())
                                .status(404)
                                .timestamp(
                                        LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleException(
            Exception ex) {

        return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.builder()
                                .message(
                                        ex.getMessage())
                                .status(500)
                                .timestamp(
                                        LocalDateTime.now())
                                .build());
    }
}