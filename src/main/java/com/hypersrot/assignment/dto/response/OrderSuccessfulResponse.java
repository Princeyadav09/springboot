package com.hypersrot.assignment.dto.response;

import lombok.Data;

@Data
public class OrderSuccessfulResponse {

    private String userId;
    private String orderId;
    private String transactionId;
    private String status;
}
