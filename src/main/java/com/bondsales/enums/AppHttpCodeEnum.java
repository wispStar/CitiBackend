package com.bondsales.enums;
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(0,"Success"),
    // 用户注册模块
    USER_EXISTS(-101,"User already exists"),
    // 用户登录模块
    LOGIN_ERROR(-101,"Invalid credentials"),
    // 用户退出模块
    USER_NOT_ONLINE(-101, "User isn't online"),


    // 债券查询模块
    BOND_NOT_FOUND(-102,"Bond not found"),


    // 删除竞拍数据出价模块
    DELETE_BOND_NOT_FOUND(-105,"Bond not found"),


    // =======================================================================================
    // 自定义枚举(api中文档没有, 抛出异常信息测试用)
    SYSTEM_ERROR(500,"Error"),
    // 用户注册模块
    USERNAME_NOT_NULL(-1, "Username cannot be empty"),
    PASSWORD_NOT_NULL(-2, "Password cannot be empty"),
    // 删除竞拍数据出价模块
    DELETE_BID_FAILED(-3, "Delete bid failed"),
    // 用户退出模块
    USER_NOT_EXISTS(-4, "User isn't exists");

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
