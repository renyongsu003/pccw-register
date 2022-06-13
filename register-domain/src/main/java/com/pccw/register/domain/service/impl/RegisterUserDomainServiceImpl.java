package com.pccw.register.domain.service.impl;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.domain.repository.RegisterRepository;
import com.pccw.register.domain.service.RegisterUserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserDomainServiceImpl implements RegisterUserDomainService {

    @Autowired
    private RegisterRepository registerRepository;

    @Value("${register.mailFile}")
    private String mailFile;

    @Transactional
    public void registerUser(User user){

        registerRepository.createUser(user);

        //提取出去到factory
        UserMail userMail = new UserMail();
        userMail.setEmail(user.getEmail());
        userMail.setMailFile(mailFile);
        userMail.setState(UserMail.MailState.TO_BE_SENT);

        registerRepository.createUserMail(userMail);

    }


}
