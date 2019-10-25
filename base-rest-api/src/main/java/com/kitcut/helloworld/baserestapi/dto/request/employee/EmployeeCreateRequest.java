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
    @NotEmpty
    private String name;

    @ApiModelProperty(notes = "Employee Birthday")
    @NotNull
    private Date birthday;

    @ApiModelProperty(notes = "Employee Address")
    @NotBlank
    private String address;

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;
}
