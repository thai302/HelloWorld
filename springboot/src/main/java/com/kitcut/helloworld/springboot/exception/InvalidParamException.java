package com.kitcut.helloworld.springboot.exception;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String msg){
        super(msg);
    }
}
