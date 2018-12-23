package com.imooc.error;

public interface CommonError {
    public int getErrorCode();
    public String getMessage();
    public CommonError setMessage(String message);
}
