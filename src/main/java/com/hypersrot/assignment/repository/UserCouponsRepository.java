package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.model.UserCoupons;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCouponsRepository extends MongoRepository<UserCoupons, String> {

    UserCoupons findByUserIdAndCouponId(String userId, String couponId);
}
