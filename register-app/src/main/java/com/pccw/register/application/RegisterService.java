package com.pccw.register.application;

import com.pccw.register.domain.entity.User;

public interface RegisterService {

    boolean registerUser(String name, String password,String email);

    boolean editUser(String name, String password,String email);

    boolean deactivateUser(String email);

    User getUser(String  email);

}
