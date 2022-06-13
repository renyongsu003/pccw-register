package com.pccw.register.adapter.dto;

public enum ResultCodeEnum {

    SUCCESS(2000, "操作成功"),
    ALL_SUCCESS(2001, "操作成功"),
    ERROR_SERVER(5000, "操作失败,服务端异常"),
    ERROR_INVOKE(5001, "调用其他服务接口错误"),
    ERROR_OTHER(5002, "其他错误"),
    ERROR_BUSINESS(5003, "业务异常"),
    ERROR_PARAM_ILLEGAL(5004, "参数校验失败");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" + "code=" + code + ", message='" + message + '\'' + '}';
    }

    /**
     * @author sunrenyong
     * @param code
     * @return
     */
    public static ResultCodeEnum getEnumByCode(int code) {
        ResultCodeEnum[] values = values();
        for (ResultCodeEnum resultCodeEnum : values) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
