package com.pccw.register.application.impl;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.domain.repository.RegisterRepository;
import com.pccw.register.domain.smtp.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FailedEmailSend {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private RegisterEmailSender registerEmailSender;


    @Scheduled(fixedDelay = 60*1000)
    public void sendEmail(){
        log.info("start send  unsent email ============");
        List<UserMail> unSendMails = registerRepository.listUnSendMail();
        for(UserMail mail:unSendMails){
            registerEmailSender.sendEmail(mail.getEmail());

        }
    }
}
