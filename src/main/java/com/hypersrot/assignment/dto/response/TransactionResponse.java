package com.hypersrot.assignment.dto.response;

import com.hypersrot.assignment.enums.CouponEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionResponse {
    private String orderId;
    private Long amount;
    private LocalDate date;
    private CouponEnum coupon;
    private String transactionId;
    private String status;
}
