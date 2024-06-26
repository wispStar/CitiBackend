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

    // 债券添加模块
    INVALID_NULL(-102,"Invalid null in bond's information"),
    BOND_EXISTS(-102,"User already exists"),


    // 创建竞拍模块
    // 提交竞价数据
    FAILED_CREATE_BID(-104, "Failed to create bid"),
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
    USER_NOT_EXISTS(-4, "User isn't exists"),
    // 创建竞拍模块
    BOND_NOT_EXISTS(-5, "Bond isn't exists"),
    // 提交竞价数据
    CUSIP_NOT_NULL(-6, "Cusip cannot be empty"),
    BidAmount_NOT_NULL(-7, "Bidamount cannot be empty");

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
