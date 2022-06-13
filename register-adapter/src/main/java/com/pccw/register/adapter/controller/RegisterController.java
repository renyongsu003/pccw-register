package com.pccw.register.adapter.controller;

import com.pccw.register.adapter.convert.UserDTOConvert;
import com.pccw.register.adapter.dto.ResultDTO;
import com.pccw.register.adapter.dto.UserDTO;
import com.pccw.register.adapter.param.cmd.CreateUserCmd;
import com.pccw.register.adapter.param.cmd.EditUserCmd;
import com.pccw.register.application.RegisterService;
import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.utils.VelocityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Api(tags = "用户注册相关接口")
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @Autowired
    private UserDTOConvert userDTOConvert;

    @ApiOperation("注册用户接口")
    @PostMapping("/create-user")
    public ResultDTO<Boolean> register(@Validated @RequestBody CreateUserCmd createUser){
        boolean success  = registerService.registerUser(createUser.getName(),createUser.getPassword(),createUser.getEmail());
        return new ResultDTO<Boolean>().success(success);
    }

    @ApiOperation("修改用户信息接口")
    @PostMapping("/edit-user")
    public ResultDTO<Boolean> editUser(@Validated @RequestBody EditUserCmd editUser){
        boolean success = registerService.editUser(editUser.getName(),editUser.getPassword(),editUser.getEmail());
        return new ResultDTO<Boolean>().success(success);
    }

    @ApiOperation("删除用户接口")
    @PostMapping("/deactivate-user")
    public ResultDTO<Boolean> deactivateUser(@RequestParam("email")  String email){
        boolean success = registerService.deactivateUser(email);
        return new ResultDTO<Boolean>().success(success);
    }


    @ApiOperation("获得用户信息接口")
    @GetMapping("/get-user")
    public ResultDTO<UserDTO> getUser(@RequestParam("email")  String email){
        User user = registerService.getUser(email);
        return new ResultDTO<UserDTO>().success(userDTOConvert.domian2Dto(user));
    }



}
