package com.hypersrot.assignment.model;

import com.hypersrot.assignment.enums.CouponEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Coupon {

    @Id
    private String id;
    private CouponEnum couponCode;
}
