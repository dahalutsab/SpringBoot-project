package com.aadim.project.service.impl;


import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRegistrationRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.RoleRepository;
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
    private final RoleRepository roleRepository;
//    private final UserRegistrationRequest userRegistrationRequest;

    @Override
    public UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest) {
        try {
            // Check if the username already exists
            if (loginRepository.existsByUsername(loginRequest.getUsername())) {
                throw new RuntimeException("Username is already taken");
            }

            // Create and save user
            User user = new User();
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setContactNum(userRequest.getContactNum());

            // Get the role based on the received role ID
            Role role = roleRepository.findById(userRequest.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);

            User savedUser = userRepository.save(user);

            // Create and save login details
            UserLogin userLogin = new UserLogin();
            userLogin.setUsername(loginRequest.getUsername());
            userLogin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userLogin.setUser(savedUser);
            UserLogin savedLoginDetails = loginRepository.save(userLogin);

            return new UserResponse(savedUser);
        } catch (DataIntegrityViolationException ex) {
            // Handle other data integrity violation or exceptions
            throw new RuntimeException("Error saving user and login details.", ex);
        }
    }

}
