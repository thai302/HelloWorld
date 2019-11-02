package com.kitcut.helloworld.baserestapi.dto.response.employee;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeListResponse {
    private Long id;
    private String name;
    private Date birthday;
    private String address;
}
