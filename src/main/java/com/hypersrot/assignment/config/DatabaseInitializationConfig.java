package com.hypersrot.assignment.config;

import com.hypersrot.assignment.enums.CouponEnum;
import com.hypersrot.assignment.model.Coupon;
import com.hypersrot.assignment.model.Product;
import com.hypersrot.assignment.repository.CouponRepository;
import com.hypersrot.assignment.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DatabaseInitializationConfig {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    public DatabaseInitializationConfig(MongoTemplate mongoTemplate, ProductRepository productRepository, CouponRepository couponRepository) {
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
    }

    @PostConstruct
    public void initializeDatabase() {

        productRepository.deleteAll();
        couponRepository.deleteAll();

        Product product = new Product();
        product.setOrdered(0L);
        product.setPrice(100L);
        product.setAvailable(100L);
        mongoTemplate.save(product);

        Coupon coupon1 = new Coupon();
        coupon1.setCouponCode(CouponEnum.OFF5);
        mongoTemplate.save(coupon1);

        Coupon coupon2 = new Coupon();
        coupon2.setCouponCode(CouponEnum.OFF10);
        mongoTemplate.save(coupon2);
    }
}
