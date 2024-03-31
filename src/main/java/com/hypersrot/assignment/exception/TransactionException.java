package com.hypersrot.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TransactionException extends RuntimeException {
    private String userId;
    private String orderId;
    private String transactionId;
    private String status;
    private String description;
    private HttpStatus httpStatus;
}
