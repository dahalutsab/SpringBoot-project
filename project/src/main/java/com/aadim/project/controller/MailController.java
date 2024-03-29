package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.ForgetPasswordRequest;
import com.aadim.project.dto.response.ForgotPasswordResponse;
import com.aadim.project.entity.Otp;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.OtpRepository;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.security.JwtService;
import com.aadim.project.service.impl.MailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail")
public class MailController extends BaseController {
    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    public JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    private MailServiceImpl mailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<GlobalApiResponse> forgotPassword(@RequestBody ForgetPasswordRequest request) throws MessagingException{

        String email = userRepository.getEmail(request.getEmail());
        Otp otp = new Otp();
        if(email!=null){

            Integer randomCode = ThreadLocalRandom.current().nextInt(100000, 1000000);
            otp.setEmail(request.getEmail());
            otp.setOtp(randomCode);
            otp.setCreatedDate(java.time.LocalDateTime.now());
            otp.setPurpose("Forgot Password");
            otpRepository.save(otp);

            String userEmail = request.getEmail();
            mailService.forgetPasswordMail(userEmail, randomCode);
            log.info("Otp sent to mail successfully");
        }else{
            log.warn("Email not found");
            return errorResponse(HttpStatus.NOT_FOUND, "Email Not Found!", null);
        }
        log.info("Otp sent to mail successfully");
        return successResponse(new ForgotPasswordResponse(email, otp.getOtp(), "Otp Sent Successfully!"));
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<GlobalApiResponse> validateOtp(@RequestBody ForgetPasswordRequest request){

        if(otpRepository.existsByEmail(request.getEmail())!=null){
            Integer storedOtp = otpRepository.existsByEmail(request.getEmail());
              if(storedOtp.equals(request.getOtp())){
                  log.info("Otp matched!");
                  return successResponse(new ForgotPasswordResponse(request.getEmail(), request.getOtp(), "Otp Matched!"));
        }else {
                    log.warn("Otp not matched!");
                  return errorResponse(HttpStatus.NOT_FOUND, "Otp Not Matched!", null);
              }
        }
        log.warn("Otp not found!");
        return errorResponse(HttpStatus.NOT_FOUND, "Otp Not Found!", null);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<GlobalApiResponse> resetPassword(@RequestBody ForgetPasswordRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            log.warn("Email not found");
            throw new RuntimeException("Email not found");
        }

        UserLogin userLogin = userRepository.getUserByEmail(request.getEmail()).getUserLogin();

        if (userLogin == null) {
            log.warn("User not found");
            throw new RuntimeException("User not found");
        }
        userLogin.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        userLoginRepository.save(userLogin);

        log.info("Password reset successfully");
        return successResponse(new ForgotPasswordResponse(request.getEmail(), request.getOtp(), "Password Reset Successfully!"));
    }

}
