package com.hypersrot.assignment.dto.response;

import lombok.Data;

@Data
public class TransactionExceptionResponse {

    private final String userId;
    private final String orderId;
    private final String transactionId;
    private final String status;
    private final String description;
}
