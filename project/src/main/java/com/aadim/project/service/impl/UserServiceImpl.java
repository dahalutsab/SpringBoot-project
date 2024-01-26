package com.aadim.project.service.impl;


import com.aadim.project.customExceptions.*;
import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.request.PasswordUpdateRequest;
import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.request.UserUpdateRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @Override
    public UserResponse saveUser(UserRequest userRequest, LoginRequest loginRequest) {
        try {
            // Check if the email already exists
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                throw new EmailAlreadyTakenException("Email is already taken");
            }

            // Check if the username already exists
            if (loginRepository.existsByUsername(loginRequest.getUsername())) {
                throw new UsernameAlreadyTakenException("Username is already taken");
            }

            // Create and save user
            User user = new User();
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setContactNum(userRequest.getContactNum());

            // Get the role based on the received role ID
            Role role = roleRepository.findById(userRequest.getRoleId())
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));
            user.setRole(role);

            User savedUser = userRepository.save(user);

            // Create and save login details
            UserLogin userLogin = new UserLogin();
            userLogin.setUsername(loginRequest.getUsername());
            userLogin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userLogin.setUser(savedUser);
            userLogin.setRoles(Collections.singletonList(role));
            UserLogin savedLoginDetails = loginRepository.save(userLogin);

            // Send email to the user
            mailServiceImpl.sendHtmlMail(userRequest.getEmail(), "New User Registration Complete", "Hello "+userRequest.getEmail()+ "Your Account have been registered. Thank You!");

            return new UserResponse(savedUser);

        } catch (DataIntegrityViolationException ex) {
            throw new UserSaveException("Error saving user and login details.", ex);
        } catch (MessagingException ms){
            throw new EmailSendException("Error while sending mail");
        }
    }



    @Transactional
    public Object updatePassword(PasswordUpdateRequest request) {
        UserLogin userLogin = loginRepository.getUserByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")).getUserLogin();

        if (!passwordEncoder.matches(request.getOldPassword(), userLogin.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        userLogin.setPassword(passwordEncoder.encode(request.getNewPassword()));
        loginRepository.save(userLogin);
        return null;
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
    public List<UserRequest> getAllTeachers() {
        Role teacherRole = roleRepository.findByName("TEACHER");
        Integer teacherId = teacherRole.getId();
        List<User> teachers = userRepository.getUserByRole_id(teacherId);

        // Map User entities to UserRequest DTOs
        List<UserRequest> userRequests;
        userRequests = teachers.stream()
                .map(user -> new UserRequest(
                        user.getFullName(),
                        user.getEmail(),
                        user.getContactNum(),
                        user.getRole().getId()))
                .collect(Collectors.toList());

        return userRequests;
    }


    @Override
    public List<UserRequest> getAllStudents() {
        Role studentRole = roleRepository.findByName("STUDENT");
        Integer studentRoleId = studentRole.getId();
        List<User> students = userRepository.getUserByRole_id(studentRoleId);

        // Map User entities to UserRequest DTOs
        List<UserRequest> userRequests;
        userRequests = students.stream()
                .map(user -> new UserRequest(
                        user.getFullName(),
                        user.getEmail(),
                        user.getContactNum(),
                        user.getRole().getId()))
                .collect(Collectors.toList());

        return userRequests;
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
