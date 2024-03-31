package com.hypersrot.assignment.controller;


import com.hypersrot.assignment.dto.response.*;
import com.hypersrot.assignment.enums.CouponEnum;
import com.hypersrot.assignment.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/inventory")
    public ProductResponse getShop() {
        return shopService.getShop();
    }

    @GetMapping("/fetchCoupons")
    public CouponsResponse getCoupons() {
        return shopService.getCoupons();
    }

    @PostMapping("/{userId}/order")
    public OrderResponse makeOrder(
            @PathVariable("userId") String userId,
            @RequestParam(name = "qty") Long quantity,
            @RequestParam(name = "coupon", required = false) CouponEnum coupon) {
        return shopService.makeOrder(userId, quantity, coupon);
    }

    @PostMapping("/{userId}/{orderId}/pay")
    public Object makePayment(
            @PathVariable("userId") String userId,
            @PathVariable("orderId") String orderId,
            @RequestParam(name = "amount") Long amount) throws IOException {
        return shopService.makePayment(userId, orderId, amount);
    }

    @GetMapping("/{userId}/orders")
    public List<AllOrdersResponse> getAllOrders(@PathVariable("userId") String userId) {
        return shopService.getAllOrders(userId);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public List<TransactionResponse> getTransaction(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) {
        return shopService.getTransaction(userId, orderId);
    }


}
