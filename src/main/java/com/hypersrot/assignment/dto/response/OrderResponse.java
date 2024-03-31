package com.hypersrot.assignment.dto.response;

import com.hypersrot.assignment.enums.CouponEnum;
import lombok.Data;

@Data
public class OrderResponse {
    private String orderId;
    private String userId;
    private Long quantity;
    private Long amount;
    private CouponEnum coupon;
}
