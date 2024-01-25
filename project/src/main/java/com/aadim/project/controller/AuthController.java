package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.auth.LoginRequest;
import com.aadim.project.dto.auth.LoginResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
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

            if (authentication.getPrincipal() instanceof UserLogin) {
                UserLogin userLogin = (UserLogin) authentication.getPrincipal();
                Integer userId = userLoginRepository.getUserIdByUserName(userLogin.getUsername());
                response.setUserId(userId);
                response.setRole(userLogin.getRoles().get(0));
                response.setUserName(userLogin.getUsername());
//                response.setUserId(userLogin.getUser().getId());
            }
            return successResponse(response);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

}