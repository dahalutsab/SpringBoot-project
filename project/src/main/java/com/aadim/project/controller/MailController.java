package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.ForgetPasswordRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/login")
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

//    @RequestMapping("/forget-password")
//    public ResponseEntity<GlobalApiResponse> sendVerificationCode(@RequestBody ForgetPasswordRequest request) throws MessagingException {
//        return successResponse("Verification Code is sent to provided email.");
//    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgetPasswordRequest request) throws MessagingException{

        String email = userRepository.getEmail(request.getEmail());
        Otp otp = new Otp();
        if(email!=null){

            Integer randomCode = (int) (Math.random()*9999);
            otp.setEmail(request.getEmail());
            otp.setOtp(randomCode);
            otp.setPurpose("Forgot Password");
            otpRepository.save(otp);

            String userEmail = request.getEmail();
            mailService.forgetPasswordMail(userEmail, randomCode);
        }else{
            return "Email Not Found!";
        }
        return "Verification Code is sent to provided email.";
    }

    @PostMapping("/validate-otp")
    public String validateOtp(@RequestBody ForgetPasswordRequest request){

        if(otpRepository.existsByEmail(request.getEmail())!=null){
            Integer storedOtp = otpRepository.existsByEmail(request.getEmail());
              if(storedOtp.equals(request.getOtp())){
                  return "Otp Matched!";
        }else {
                  return "Otp Not Matched!";
              }
        }
        return "Checking Otp!";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ForgetPasswordRequest request){

        Integer userId = userRepository.getUserIdByEmail(request.getEmail());

        UserLogin userLogin = userLoginRepository.findById(userId).orElseThrow(()-> new RuntimeException("User With Email Not found"));

        userLogin.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

        return "Password Reset Successfully!";
    }

}
