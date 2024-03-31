package com.hypersrot.assignment.dto.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserResponse {
    private String name;
    private String email;
    private String userId;
}
