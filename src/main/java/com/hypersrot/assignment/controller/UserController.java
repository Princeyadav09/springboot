package com.hypersrot.assignment.controller;

import com.hypersrot.assignment.dto.request.UserRequest;
import com.hypersrot.assignment.dto.response.UserResponse;
import com.hypersrot.assignment.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest user) {
        return userService.login(user);
    }
}
