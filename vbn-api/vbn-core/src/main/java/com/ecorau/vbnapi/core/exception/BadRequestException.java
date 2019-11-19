package com.ecorau.vbnapi.core.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){
        super(msg);
    }
}
