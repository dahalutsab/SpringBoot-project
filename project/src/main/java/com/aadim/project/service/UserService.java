package com.aadim.project.service;

import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.User;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest);


    UserResponse getById(Integer id);

    List<User> getAllPersons();

    UserResponse updateUser(UserUpdateRequest request);

    String deleteStudent(Integer id);
}
