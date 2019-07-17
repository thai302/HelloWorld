package com.kitcut.helloworld.baserestapi.dto.response.employee;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse {
    private String message;
    private Integer code;
    private Object data;

    public static BaseResponse ok(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setCode(HttpStatus.OK.value());
//        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        return baseResponse;
    }
}
