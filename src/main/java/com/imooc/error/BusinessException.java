package com.imooc.error;

public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    public BusinessException(CommonError commonError) {
//        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String message) {
//        super();
        this.commonError = commonError;
        this.commonError.setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getMessage() {
        return commonError.getMessage();
    }

    @Override
    public CommonError setMessage(String message) {
        this.commonError.setMessage(message);
        return this;
    }
}
