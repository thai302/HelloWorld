package com.kitcut.helloworld.baserestapi.dto.request.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@ApiModel(description = "All details about the Employee. ")
public class EmployeeCreateRequest {
    @ApiModelProperty(notes = "Employee Name")
    @Size(min = 4, max = 50)
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Employee Birthday")
    private Date birthday;

    @ApiModelProperty(notes = "Employee Address")
    private String address;
}
