package com.pccw.register.adapter.dto;

import com.pccw.register.domain.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户名",example = "苏仁永")
    private String name;
    @ApiModelProperty(value = "邮箱",example = "renyongsu@sohu.com")
    private String email;

    @ApiModelProperty(value = "用户状态,0:无效，1:有效",example = "1")
    private Integer state;


}
