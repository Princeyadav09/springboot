package com.hypersrot.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class OrderException extends RuntimeException {
    private final String orderId;
    private final String description;
    private final HttpStatus httpStatus;
}
