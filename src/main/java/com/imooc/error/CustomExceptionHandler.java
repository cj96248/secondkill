package com.imooc.error;

import com.imooc.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String, Object> data = new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException be = (BusinessException) ex;
            data.put("code", be.getErrorCode());
            data.put("message", be.getMessage());
        }else {
            data.put("code", EnumError.UNKNOWN_ERROR.getErrorCode());
            data.put("message", EnumError.UNKNOWN_ERROR.getMessage());
        }
        return new CommonReturnType("fail",data);
    }
}
