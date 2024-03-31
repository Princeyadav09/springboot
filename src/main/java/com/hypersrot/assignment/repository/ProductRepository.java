package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
