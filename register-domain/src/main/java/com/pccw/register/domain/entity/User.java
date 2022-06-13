package com.pccw.register.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private String name;

    private String email;

    private String password;

    private UserState state;


    @AllArgsConstructor
    @Getter
    public  enum UserState{
        NORMAL(1,"正常"),
        DELETED(0,"已删除");

        private Integer code;
        private String desc;

    }


}
