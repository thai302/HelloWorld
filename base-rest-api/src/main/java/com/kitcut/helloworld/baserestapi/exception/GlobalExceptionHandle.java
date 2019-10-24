package com.kitcut.helloworld.baserestapi.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<Result<Object>> handleExceptions(Exception e) {
//        e.printStackTrace();
//        if (e.getClass() == DefenseException.class) {
//            DefenseException ex = (DefenseException) e;
//            return ResponseEntity.ok().header("error", "Global unhandle exceptions")
//                    .body(Result.createFail(ex));
//        }
//
//        return ResponseEntity.ok().header("error", "Global unhandle exceptions")
//                .body(new Result<>().code(ResultCode.FAILED).message(e.getMessage()));
//    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<String> handleExceptions(Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.badRequest()
//                .body(e.getMessage());
//    }

//    @ExceptionHandler(NotFoundException.class)
//    public void handleExceptions(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value());
//    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(value= HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ResponseEntity<String> requestHandlingNoHandlerFound() {
//        return new ResponseEntity<>("haha", HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(value=HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ResponseEntity<String> requestHandlingNoHandlerFound(final NoHandlerFoundException ex) {
//        return new ResponseEntity<>("haha", HttpStatus.NOT_FOUND);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return validateBindingException(ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return validateBindingException(ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = (MethodArgumentTypeMismatchException) ex;
        String error = methodArgumentTypeMismatchException.getName() + System.lineSeparator() + methodArgumentTypeMismatchException.getCause().getMessage();

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    private ResponseEntity<Object> validateBindingException(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}

