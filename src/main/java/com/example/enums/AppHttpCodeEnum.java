package com.example.enums;
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(0,"Success"),
    SYSTEM_ERROR(500,"Error"),
    //  用户注册模块
    USERNAME_NOT_NULL(-1, "Username cannot be empty"),
    PASSWORD_NOT_NULL(-2, "Password cannot be empty"),
    USER_EXISTS(-101,"User already exists");

    int code;
    String msg;
    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }
    public int getCode() {
    return code;
    }
    public String getMsg() {
    return msg;
    }
}
