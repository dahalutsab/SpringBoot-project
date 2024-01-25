package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.ForgetPasswordRequest;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.security.JwtService;
import com.aadim.project.service.impl.MailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/login")
public class MailController extends BaseController {

    @Autowired
    public JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    private MailServiceImpl mailService;

//    @RequestMapping("/forget-password")
//    public ResponseEntity<GlobalApiResponse> sendVerificationCode(@RequestBody ForgetPasswordRequest request) throws MessagingException {
//        return successResponse("Verification Code is sent to provided email.");
//    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgetPasswordRequest request) throws MessagingException{

        String email = userRepository.getEmail(request.getEmail());
        if(email!=null){
            String token = jwtService.generateToken(email);

            // Inside your controller or service method
            String userEmail = request.getEmail(); // Replace with the actual user's email
            String resetLink = "http:localhost:8899/api/v1/login/reset-password?token="+token; // Replace with the actual reset link
            mailService.forgetPasswordMail(userEmail, resetLink);
        }else {
            return "Email not found";
        }
        return "Email sent successfully";
    }

    @GetMapping("/reset-password")
    public String resetPassword(){
        return "Login Success!";
    }
}
