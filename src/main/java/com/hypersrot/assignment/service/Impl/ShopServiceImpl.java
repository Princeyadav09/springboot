package com.hypersrot.assignment.service.Impl;

import com.hypersrot.assignment.dto.response.*;
import com.hypersrot.assignment.enums.CouponEnum;
import com.hypersrot.assignment.exception.BusinessException;
import com.hypersrot.assignment.exception.OrderException;
import com.hypersrot.assignment.exception.TransactionException;
import com.hypersrot.assignment.model.*;
import com.hypersrot.assignment.repository.*;
import com.hypersrot.assignment.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ShopServiceImpl implements ShopService {
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final UserCouponsRepository userCouponsRepository;

    public ShopServiceImpl(ProductRepository productRepository, CouponRepository couponRepository, UserRepository userRepository, OrderRepository orderRepository, TransactionRepository transactionRepository, UserCouponsRepository userCouponsRepository) {
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
        this.userCouponsRepository = userCouponsRepository;
    }

    @Override
    public ProductResponse getShop() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BusinessException("There is no product available !", HttpStatus.NOT_FOUND);
        }
        ProductResponse productResponse = new ProductResponse();
        Product product = products.get(0);
        productResponse.setAvailable(product.getAvailable());
        productResponse.setPrice(product.getPrice());
        productResponse.setOrdered(product.getOrdered());
        return productResponse;
    }

    @Override
    public CouponsResponse getCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        if (coupons.isEmpty()) {
            throw new BusinessException("There is no coupons available !", HttpStatus.NOT_FOUND);
        }
        CouponsResponse couponsResponse = new CouponsResponse();
        for (Coupon coupon : coupons) {
            if (coupon.getCouponCode().equals(CouponEnum.OFF5)) {
                couponsResponse.setOFF5(5L);
            } else {
                couponsResponse.setOFF10(10L);
            }
        }
        return couponsResponse;
    }

    @Override
    public List<TransactionResponse> getTransaction(String userId, String orderId) {
        List<Transaction> transactionList = transactionRepository.findAllByOrderId(orderId);
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderException(orderId, "Order not found", HttpStatus.NOT_FOUND);
        }
        Order order1 = order.get();
        List<TransactionResponse> responses = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setAmount(order1.getAmount());
            transactionResponse.setOrderId(order1.getId());
            transactionResponse.setCoupon(order1.getCoupon());
            transactionResponse.setDate(order1.getDate());
            transactionResponse.setStatus(transaction.getStatus());
            transactionResponse.setTransactionId(transaction.getTransactionId());
            responses.add(transactionResponse);
        }
        return responses;

    }

    @Override
    public List<AllOrdersResponse> getAllOrders(String userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<AllOrdersResponse> responses = new ArrayList<>();
        for (Order order : orderList) {
            AllOrdersResponse allOrdersResponse = new AllOrdersResponse();
            allOrdersResponse.setAmount(order.getAmount());
            allOrdersResponse.setOrderId(order.getId());
            allOrdersResponse.setCoupon(order.getCoupon());
            allOrdersResponse.setDate(order.getDate());
            responses.add(allOrdersResponse);
        }
        return responses;
    }

    @Override
    public Object makePayment(String userId, String orderId, Long amount) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderException(orderId, "Order not found", HttpStatus.NOT_FOUND);
        }
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setOrderId(orderId);
        Random random = new Random();
        int randomIndex = random.nextInt(6);
        if (randomIndex == 0) {
            transaction.setTransactionId("tran010100001");
            transaction.setStatus("Successful");
            transactionRepository.save(transaction);
            OrderSuccessfulResponse orderSuccessfulResponse = new OrderSuccessfulResponse();
            orderSuccessfulResponse.setOrderId(orderId);
            orderSuccessfulResponse.setUserId(userId);
            orderSuccessfulResponse.setTransactionId(transaction.getTransactionId());
            orderSuccessfulResponse.setStatus(transaction.getStatus());
            return orderSuccessfulResponse;
        } else if (randomIndex == 1) {
            transaction.setTransactionId("tran010100002");
            transaction.setStatus("Failed");
            transactionRepository.save(transaction);
            throw new TransactionException(orderId, userId, transaction.getTransactionId(), transaction.getStatus(), "Payment Failed as amount is invalid", HttpStatus.BAD_REQUEST);
        } else if (randomIndex == 2) {
            transaction.setTransactionId("tran010100003");
            transaction.setStatus("Failed");
            transactionRepository.save(transaction);
            throw new TransactionException(orderId, userId, transaction.getTransactionId(), transaction.getStatus(), "Payment Failed from bank", HttpStatus.BAD_REQUEST);
        } else if (randomIndex == 3) {
            transaction.setTransactionId("tran010100004");
            transaction.setStatus("Failed");
            transactionRepository.save(transaction);
            throw new TransactionException(orderId, userId, transaction.getTransactionId(), transaction.getStatus(), "Payment Failed due to invalid order id", HttpStatus.BAD_REQUEST);
        } else if (randomIndex == 4) {
            transaction.setTransactionId("tran010100005");
            transaction.setStatus("Failed");
            transactionRepository.save(transaction);
            throw new TransactionException(orderId, userId, transaction.getTransactionId(), transaction.getStatus(), "No response from payment server", HttpStatus.GATEWAY_TIMEOUT);
        } else {
            transaction.setTransactionId("tran010100006");
            transaction.setStatus("Failed");
            transactionRepository.save(transaction);
            throw new TransactionException(orderId, userId, transaction.getTransactionId(), transaction.getStatus(), "Order is already paid for", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @Override
    public OrderResponse makeOrder(String userId, Long quantity, CouponEnum coupon) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new BusinessException("UserId not found", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        List<Product> products = productRepository.findAll();
        Product product = products.get(0);
        if (product.getAvailable() <= quantity || quantity<1) {
            throw new BusinessException("Invalid quantity", HttpStatus.NOT_FOUND);
        }
        product.setOrdered(product.getOrdered() + quantity);
        product.setAvailable(product.getAvailable() - quantity);
        Order order = new Order();
        Long price = quantity * product.getPrice();
        if (coupon != null) {
            Coupon couponDetail = couponRepository.findByCouponCode(coupon);
            UserCoupons userCoupons = userCouponsRepository.findByUserIdAndCouponId(userId,couponDetail.getId());
            if (userCoupons==null) {
                price = applyCoupon(coupon,price);
                userCoupons = new UserCoupons();
                userCoupons.setCouponId(couponDetail.getId());
                userCoupons.setUserId(userId);
                userCouponsRepository.save(userCoupons);
            } else {
                throw new BusinessException("Invalid coupon", HttpStatus.NOT_FOUND);
            }
        }
        order.setAmount(price);
        order.setDate(LocalDate.now());
        order.setUserId(userId);
        order.setCoupon(coupon);
        Order savedOrder = orderRepository.save(order);
        productRepository.save(product);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setAmount(savedOrder.getAmount());
        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setQuantity(quantity);
        orderResponse.setUserId(userId);
        orderResponse.setCoupon(savedOrder.getCoupon());
        return orderResponse;
    }

    public Long applyCoupon(CouponEnum coupon, Long price) {
        if (CouponEnum.OFF5.equals(coupon)) {
            return applyPercentageDiscount(price, 5);
        } else if (CouponEnum.OFF10.equals(coupon)) {
            return applyPercentageDiscount(price, 10);
        } else {
            return price;
        }
    }

    private Long applyPercentageDiscount(Long price, int percentage) {
        double discount = (double) price * percentage / 100;
        return price - Math.round(discount);
    }


}
