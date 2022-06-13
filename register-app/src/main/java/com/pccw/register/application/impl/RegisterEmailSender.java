package com.pccw.register.application.impl;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.domain.repository.RegisterRepository;
import com.pccw.register.domain.smtp.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterEmailSender {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private EmailSender emailSender;

    public void sendEmail(String email){
        log.info("start send mail ----------");
        UserMail userMail =  registerRepository.getUserMail(email);
        User user  = registerRepository.getUser(email);

        emailSender.sendRegisterReply(userMail,user);

        registerRepository.updateUserMailState(userMail);

    }

}
