package com.ecorau.vbnapi.core.dto.response.employee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeBaseResponse {
    @ApiModelProperty(notes = "Employee Id", example = "1")
    private Long id;

    @ApiModelProperty(notes = "Employee Name", example = "thaidh")
    private String name;

    @ApiModelProperty(notes = "Employee Birthday", example = "2010-01-01")
    private Date birthday;

    @ApiModelProperty(notes = "Employee Address", example = "NA")
    private String address;
}
