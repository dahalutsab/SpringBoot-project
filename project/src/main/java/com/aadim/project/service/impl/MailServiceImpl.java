package com.aadim.project.service.impl;

import com.aadim.project.dto.request.ForgetPasswordRequest;
import com.aadim.project.entity.User;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;


    @Async
    @Override
    public void sendHtmlMail(String to, String sub, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(sub);
        Context context = new Context();
        context.setVariable("content", content);
        String htmlContent = templateEngine.process("email-template.html",
                context);
        helper.setText(htmlContent,true);
        javaMailSender.send(message);
    }

    @Async
    @Override
    public void forgetPasswordMail(String toEmail, Integer verificationCode) throws MessagingException{

        String email = userRepository.getEmail(toEmail);

        try{
            if(email.equals(toEmail)){
                String sub = "Password Verification Code";
                String content = "Hello "+toEmail+" Your Verification code is :" + verificationCode;
                sendHtmlMail(toEmail, sub, content);
            }
        }catch (Exception e){
            log.error("Error in sending mail: {}", e.getMessage());
        }



    }



}
