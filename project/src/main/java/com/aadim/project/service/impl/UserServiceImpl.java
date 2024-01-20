package com.aadim.project.service.impl;


import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest) {
        try {
            // Create and save user
            User user = new User();
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setContactNum(userRequest.getContactNum());
            User savedUser = userRepository.save(user);

            // Create and save login details
            UserLogin userLogin = new UserLogin();
            userLogin.setUsername(loginRequest.getUsername());
//            userLogin.setPassword(loginRequest.getPassword());
            userLogin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userLogin.setUser(savedUser); // Set the relationship
            UserLogin savedLoginDetails = loginRepository.save(userLogin);

            return new UserResponse(savedUser);
        } catch (DataIntegrityViolationException ex) {
            // Handle data integrity violation or other exceptions
            throw new RuntimeException("Error saving user and login details.", ex);
        }
    }
}
