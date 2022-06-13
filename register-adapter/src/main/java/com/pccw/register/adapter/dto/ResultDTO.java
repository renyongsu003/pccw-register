package com.pccw.register.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResultDTO <T>{

    @ApiModelProperty(value = "返回状态码")
    private Integer code;

    @ApiModelProperty(value = "返回状态说明")
    private String message;

    @ApiModelProperty(value = "返回数据体")
    private T data;

    public ResultDTO(){

    }

    public ResultDTO(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public ResultDTO(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public  ResultDTO<T> success(T data){
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
        return this;
    }

    public ResultDTO fail(ResultCodeEnum resultCode) {
        return this.fail(resultCode, resultCode.getMessage());
    }

    public ResultDTO fail(ResultCodeEnum resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
        return this;
    }




}
