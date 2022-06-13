package com.pccw.register.infrastructure.smtp;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.domain.smtp.EmailSender;
import com.pccw.register.domain.utils.ConstantsUtils;
import com.pccw.register.domain.utils.VelocityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

@Component
@Slf4j
public class EmailSenderImpl implements EmailSender {

    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityUtils velocityUtils;

    @Override
    public boolean sendRegisterReply(UserMail userMail, User user) {
        String mailContent = velocityUtils.getString(userMail.getMailFile(),user.getName());
        log.info("mail content:\n {}",mailContent);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("renyongsu@sohu.com");
        message.setTo(userMail.getEmail());
        message.setSubject(ConstantsUtils.MAIL_SUBJECT);
        message.setText(mailContent);
        javaMailSender.send(message);
        log.info("mail sent.........");
        return true;
    }
}
