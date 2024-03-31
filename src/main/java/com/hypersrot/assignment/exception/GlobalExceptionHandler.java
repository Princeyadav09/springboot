package com.hypersrot.assignment.exception;

import com.hypersrot.assignment.dto.response.OrderExceptionResponse;
import com.hypersrot.assignment.dto.response.TransactionExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(final BusinessException exception, final HttpServletRequest request) {
        return new ResponseEntity<>(exception.getDescription(), exception.getHttpStatus());
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<?> handleOrderException(final OrderException exception, final HttpServletRequest request) {
        OrderExceptionResponse response = new OrderExceptionResponse(
                exception.getOrderId(),
                exception.getDescription()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> handleTransactionException(final TransactionException exception, final HttpServletRequest request) {
        TransactionExceptionResponse response = new TransactionExceptionResponse(
                exception.getOrderId(),
                exception.getUserId(),
                exception.getTransactionId(),
                exception.getStatus(),
                exception.getDescription()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(final Exception exception, final HttpServletRequest request) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
