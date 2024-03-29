package com.aadim.project.dto.auth;

import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Role role;
    private Integer userId;
    private String userName;
}
