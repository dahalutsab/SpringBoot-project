package com.aadim.project.service;

import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.response.UserResponse;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest);
}
