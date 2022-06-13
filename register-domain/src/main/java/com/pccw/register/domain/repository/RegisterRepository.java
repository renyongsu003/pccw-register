package com.pccw.register.domain.repository;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;

import java.util.List;

public interface RegisterRepository {

    boolean createUser(User user);

    boolean createUserMail(UserMail userMail);

    boolean existUser(String email);

    User getUser(String email);

    List<UserMail> listUnSendMail();

    UserMail getUserMail(String email);

    boolean editUser(User user);

    boolean deactivateUser(String email);

    boolean updateUserMailState(UserMail userMail);

}
