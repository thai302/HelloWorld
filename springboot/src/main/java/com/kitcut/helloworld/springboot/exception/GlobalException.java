package com.kitcut.helloworld.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidException(InvalidParamException exception) {
        return new ResponseEntity<>("Invalid parameter", HttpStatus.BAD_REQUEST);
    }
}
