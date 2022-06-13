package com.pccw.register.infrastructure.po;

import com.pccw.register.domain.entity.User.UserState;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;


/**
 * @Description pccw_user
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private UserState state;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}