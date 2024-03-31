package com.hypersrot.assignment.dto.response;

import com.hypersrot.assignment.enums.CouponEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AllOrdersResponse {
    private String orderId;
    private Long amount;
    private LocalDate date;
    private CouponEnum coupon;
}
