package com.aadim.project.service;

import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.PasswordUpdateRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest);


    UserResponse getById(Integer id);

    List<UserResponse> getAllUsers();


    List<UserRequest> getAllTeachers();

    List<UserRequest> getAllStudents();

    UserResponse updateUser(UserUpdateRequest request);

    String deleteStudent(Integer id);

    Object updatePassword(PasswordUpdateRequest request);
}
