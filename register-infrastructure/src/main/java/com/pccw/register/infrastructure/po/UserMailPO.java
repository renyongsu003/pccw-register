package com.pccw.register.infrastructure.po;

import com.pccw.register.domain.entity.UserMail.MailState;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description pccw_user_mail
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMailPO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String mailFile;

    /**
     * 
     */
    private MailState state;

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