package com.aadim.project.service;

import com.aadim.project.dto.MailDto;
import jakarta.mail.MessagingException;

public interface MailService {

    void sendHtmlMail(MailDto mailDto) throws MessagingException;
}
