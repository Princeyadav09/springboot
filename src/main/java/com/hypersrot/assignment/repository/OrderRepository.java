package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByUserId(String userId);

}
