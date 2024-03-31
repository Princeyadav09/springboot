package com.hypersrot.assignment.service;

import com.hypersrot.assignment.dto.request.UserRequest;
import com.hypersrot.assignment.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponse login(UserRequest user);
}

