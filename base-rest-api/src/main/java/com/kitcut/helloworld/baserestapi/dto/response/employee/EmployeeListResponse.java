package com.kitcut.helloworld.baserestapi.dto.response.employee;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Employee list response")
public class EmployeeListResponse extends EmployeeBaseResponse {
}
