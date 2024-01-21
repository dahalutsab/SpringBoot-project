package com.aadim.project.service.impl;


import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.RoleService;
import com.aadim.project.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailServiceImpl mailServiceImpl;
    private final RoleService roleService;
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
            userLogin.setFullName(userRequest.getFullName());
            userLogin.setUsername(loginRequest.getUsername());
            userLogin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userLogin.setUser(savedUser);
            UserLogin savedLoginDetails = loginRepository.save(userLogin);

            mailServiceImpl.sendHtmlMail(userRequest.getEmail(), "Registration Complete", "Hello "+userRequest.getEmail()+ "Your Account have been registered. Thank You!");

            return new UserResponse(savedUser);
        } catch (DataIntegrityViolationException ex) {
            // Handle other data integrity violation or exceptions
            throw new RuntimeException("Error saving user and login details.", ex);
        } catch (MessagingException ms){
            throw new RuntimeException("Error while sending mail");
        }
    }


    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.getReferenceById(id);
        return new UserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
    }

    @Override
    public List<User> getAllTeachers() {
//        Role teacherRole = roleRepository.findByName("TEACHER");
//        return userRepository.findByRole(teacherRole);
        roleService.getRoleOfTeacher();
        return userRepository.findAll();
    }




    @Override
    public UserResponse updateUser(UserUpdateRequest request) {
        User user = userRepository.getReferenceById(request.getId());

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setContactNum(request.getContactNum());

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }


    @Override
    public String deleteStudent(Integer id){
        userRepository.deleteById(id);
        return "Deleted Student With id : "+id+" Successfully!";
    }
}
