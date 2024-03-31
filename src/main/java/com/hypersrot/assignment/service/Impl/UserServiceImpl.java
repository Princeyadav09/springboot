package com.hypersrot.assignment.service.Impl;

import com.hypersrot.assignment.dto.request.UserRequest;
import com.hypersrot.assignment.dto.response.UserResponse;
import com.hypersrot.assignment.model.User;
import com.hypersrot.assignment.repository.UserRepository;
import com.hypersrot.assignment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse login(UserRequest user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(user1.getId());
            userResponse.setEmail(user1.getEmail());
            userResponse.setName(user1.getName());
            return userResponse;
        } else {
            User user2 = new User();
            user2.setEmail(user.getEmail());
            user2.setName(user.getName());
            User user3 = userRepository.save(user2);
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user3.getName());
            userResponse.setEmail(user3.getEmail());
            userResponse.setUserId(user3.getId());
            return userResponse;
        }
    }


}
