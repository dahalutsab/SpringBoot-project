package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.auth.LoginResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/login")
public class AuthController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserLoginRepository userLoginRepository;


    @PostMapping
    public ResponseEntity<GlobalApiResponse> authenticateAndGetToken(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            LoginResponse response = new LoginResponse();
            String token = jwtService.generateToken(request.getUsername());
            response.setToken(token);
            Role role = userLoginRepository.getUserRoleByUsername(request.getUsername());
            Integer userId = userLoginRepository.getUserIdByUsername(request.getUsername());
            String userName = userLoginRepository.getUserNameByUsername(request.getUsername());
            response.setUserName(userName);
            response.setUserId(userId);
            if (role == null){
                response.setRole("ADMIN");
            }else {
                response.setRole(role.getName());
            }

            return successResponse(response);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

}