package com.imooc.error;

public enum EnumError implements CommonError {
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    PARAMETER_NONNULL_ERROR(10002, "参数不能为空"),
    UNKNOWN_ERROR(10002, "未知错误"),
    PRODUCT_NOT_EXIST(30001, "商品不存在"),
    PRODUCT_NOT_ENOUGH(30002, "库存不足"),
    PROMO_NOT_EXIST(30001, "秒杀活动不存在"),
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_ERROR(20003, "用户/密码不正确"),
    USER_NOT_LOGIN(20004, "用户未登录"),
    USER_ALREADY_EXIST(20002, "号码已注册");


    private int errorCode;
    private String message;

     EnumError(int errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public CommonError setMessage(String message) {
        this.message = message;
        return this;
    }
}
