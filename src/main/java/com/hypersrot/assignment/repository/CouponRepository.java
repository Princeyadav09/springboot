package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.enums.CouponEnum;
import com.hypersrot.assignment.model.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon, String> {

    Coupon findByCouponCode(CouponEnum couponEnum);
}
