package com.dirge.utils;

import com.dirge.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;


@Configuration
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     *
     * @param email
     * @return
     */
    public boolean sendSimpleMail(Email email){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email.getSender());
        mailMessage.setTo(email.getReceiver());
        mailMessage.setSubject(email.getTitle());
        mailMessage.setText(email.getContent());
        mailMessage.setSentDate(email.getSendDate());
        javaMailSender.send(mailMessage);
        return true;
    }
}
