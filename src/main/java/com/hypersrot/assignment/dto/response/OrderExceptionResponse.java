package com.hypersrot.assignment.dto.response;

import lombok.Data;

@Data
public class OrderExceptionResponse {
    private final String orderId;
    private final String description;
}
