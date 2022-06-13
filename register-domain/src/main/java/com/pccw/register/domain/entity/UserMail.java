package com.pccw.register.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMail {

    private String email;

    private String mailFile;

    private MailState state;



    @AllArgsConstructor
    @Getter
    public  enum MailState{
        SENDED(1,"已发送"),
        TO_BE_SENT(0,"待发送");

        private Integer code;
        private String desc;

    }

}
