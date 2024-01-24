package com.aadim.project.service.impl;

import com.aadim.project.dto.request.ForgetPasswordRequest;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    @Async
    public void sendHtmlMail(String to, String sub, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(sub);
//        helper.addAttachment(mailDto.getFile().getOriginalFilename(),     For files and attachments
//                mailDto.getFile());
        Context context = new Context();
        context.setVariable("content", content);
        String htmlContent = templateEngine.process("email-template.html",
                context);
        helper.setText(htmlContent,true);
        javaMailSender.send(message);
    }

    @Override
    public void forgetPasswordMail(String toEmail, String resetLink) throws MessagingException{

        if(toEmail.equals(userLoginRepository.getUserNameByEmail(toEmail))){
            String sub = "Password Reset Request";
            String content = "Click the link to reset your password: " + resetLink;
            sendHtmlMail(toEmail, sub, content);
        }else{
            throw new MessagingException("Email Not Found!");
        }



    }



}
