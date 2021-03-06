package com.kitcut.helloworld.baserestapi.dto.response.employee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse<T> {
    @ApiModelProperty(example = "Success")
    private String message;

    @ApiModelProperty(example = "200")
    private Integer code;

    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setCode(HttpStatus.OK.value());
//        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        return baseResponse;
    }
}
