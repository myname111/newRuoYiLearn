package com.ruoyi.common.exception;
//业务异常
public class BusinessException extends RuntimeException{
    private String message;
    public BusinessException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
