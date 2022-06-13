package com.pccw.register.adapter.convert;

import com.pccw.register.adapter.dto.UserDTO;
import com.pccw.register.domain.entity.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConvert {

    public UserDTO domian2Dto(User user){
        return user!=null ? new UserDTO(user.getName(),user.getEmail(),user.getState().getCode()):null;
    }


}
