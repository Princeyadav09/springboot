package com.hypersrot.assignment.service;

import com.hypersrot.assignment.dto.response.*;
import com.hypersrot.assignment.enums.CouponEnum;

import java.util.List;

public interface ShopService {


    ProductResponse getShop();

    CouponsResponse getCoupons();

    List<TransactionResponse> getTransaction(String userId, String orderId);

    List<AllOrdersResponse> getAllOrders(String userId);

    Object makePayment(String userId, String orderId, Long amount);

    OrderResponse makeOrder(String userId, Long quantity, CouponEnum coupon);
}
