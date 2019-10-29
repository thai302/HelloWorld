package com.kitcut.helloworld.baserestapi.dto.response.employee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kitcut.helloworld.baserestapi.config.JsonDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(description = "All details about the Employee. ")
public class EmployeeCreateResponse {
    private Long id;
    @ApiModelProperty(notes = "Employee Name", example = "hahah")
    private String name;
    private Date birthday;
    private String address;

    @JsonSerialize(using = JsonDateSerializer.DateTimeJsonSerializer.class)
    private Date cmnCreatedAt;
}
