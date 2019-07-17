package com.kitcut.helloworld.baserestapi.dto.response.employee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kitcut.helloworld.baserestapi.config.JsonDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeCreateResponse {
    private Long id;
    private String name;
    private Date birthday;
    private String address;

    @JsonSerialize(using = JsonDateSerializer.DateTimeJsonSerializer.class)
    private Date cmnCreatedAt;
}
