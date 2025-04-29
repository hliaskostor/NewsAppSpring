package com.example.register.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
  JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String body) throws MessagingException {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
        helper.setText(body, true);
            helper.setSubject("Καλώς ήρθατε στην εφαρμογή! | Welcome to the application!");
            helper.setText(body, true);
            javaMailSender.send(message);


        }
    }
