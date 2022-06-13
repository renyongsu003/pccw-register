package com.pccw.register.adapter.param.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class CreateUserCmd {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3,max = 10,message = "用户名的长度在3-10之间")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @NotBlank(message = "密码不能为空")
    @Size(min = 3,max = 10,message = "密码的长度需在3-10之间")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "用户邮箱", required = true)
    private String email;

}
