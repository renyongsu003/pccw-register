package com.pccw.register.infrastructure.convert;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.utils.PasswordSec;
import com.pccw.register.infrastructure.po.UserPO;
import org.springframework.stereotype.Component;

/**
 * 可以考虑使用MapStruct等
 */
@Component
public class UserConvert {

    public UserPO covertDomainToPO(User user){
        UserPO userPO = new UserPO();
        userPO.setEmail(user.getEmail());
        userPO.setName(user.getName());
        userPO.setPassword(PasswordSec.sec(user.getPassword()));
        userPO.setState(user.getState());
        return userPO;
    }


    public User covertPOToDomain(UserPO userPO){
        User user = new User();
        user.setEmail(userPO.getEmail());
        user.setName(userPO.getName());
        user.setState(userPO.getState());
        return user;
    }


}
