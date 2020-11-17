package com.dirge.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     *
     * @param sender        发送者
     * @param receiver      接收者
     * @param title         邮件标题
     * @param content       邮件内容
     * @return
     */
    public boolean sendSimpleMail(String sender,String receiver,String title,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);
        return true;
    }
}
