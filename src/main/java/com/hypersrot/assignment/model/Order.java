package com.hypersrot.assignment.model;

import com.hypersrot.assignment.enums.CouponEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Order {

    @Id
    private String id;
    private String userId;
    private Long amount;
    private LocalDate date;
    private CouponEnum coupon;
}
