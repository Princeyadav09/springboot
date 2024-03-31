package com.hypersrot.assignment.repository;

import com.hypersrot.assignment.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}
