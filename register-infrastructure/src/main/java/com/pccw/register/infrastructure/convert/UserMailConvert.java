package com.pccw.register.infrastructure.convert;

import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.infrastructure.po.UserMailPO;
import com.pccw.register.infrastructure.po.UserPO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMailConvert {

    public UserMail covertPOToDomain(UserMailPO userMailPO){
        UserMail userMail = new UserMail();
        userMail.setEmail(userMailPO.getEmail());
        userMail.setMailFile(userMailPO.getMailFile());

        return userMail;
    }

    public List<UserMail> covertPOSToDomains(List<UserMailPO> userMailPOS){

        return userMailPOS.stream()
                .map(po -> covertPOToDomain(po))
                .collect(Collectors.toList());
    }


    public UserMailPO covertDomainToPO(UserMail userMail){
        UserMailPO userMailPO = new UserMailPO();
        userMailPO.setEmail(userMail.getEmail());
        userMailPO.setMailFile(userMail.getMailFile());
        userMailPO.setState(UserMail.MailState.TO_BE_SENT);
        return userMailPO;
    }
}
