package com.pccw.register.domain.smtp;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;

public interface EmailSender {


    boolean sendRegisterReply(UserMail userMail, User user);


}
