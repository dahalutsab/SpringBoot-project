package com.aadim.project.service.impl;

import com.aadim.project.dto.MailDto;
import com.aadim.project.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    @Async
    public void sendHtmlMail(MailDto mailDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(mailDto.getTo());
        helper.setSubject(mailDto.getSub());
//        helper.addAttachment(mailDto.getFile().getOriginalFilename(),     For files and attachments
//                mailDto.getFile());
        Context context = new Context();
        context.setVariable("content", mailDto.getContent());
        String htmlContent = templateEngine.process("email-template.html",
                context);
        helper.setText(htmlContent,true);
        javaMailSender.send(message);
    }

}
