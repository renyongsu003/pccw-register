package com.pccw.register.application.impl;

import com.pccw.register.application.RegisterService;
import com.pccw.register.domain.vo.SendEmailMsg;
import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.mq.MsgSender;
import com.pccw.register.domain.repository.RegisterRepository;
import com.pccw.register.domain.service.RegisterUserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private RegisterUserDomainService registerUserDomainService;

    @Autowired
    private MsgSender msgSender;

    @Value("${kafka.email.topic}")
    private String emailTopic;

    @Override
    public boolean registerUser(String name, String password,String email) {
        User user  = new User(name,email, password,User.UserState.NORMAL);
        registerUserDomainService.registerUser(user);
        msgSender.sendMessage(emailTopic,new SendEmailMsg(email));
        return true;
    }

    @Override
    public boolean editUser(String name,String password,String email){

        User user  = new User(name,email,password,User.UserState.NORMAL);
        return registerRepository.editUser(user);

    }

    @Override
    public boolean deactivateUser(String email){
        return registerRepository.deactivateUser(email);
    }

    @Override
    public User getUser(String email) {
        User user =  registerRepository.getUser(email);
        return user;
    }


}
