package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findAllByOrderId(String orderId);
}
