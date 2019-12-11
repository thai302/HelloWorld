package com.kitcut.helloworld.baserestapi.dto.request.employee;

import com.kitcut.helloworld.baserestapi.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ApiModel(description = "All details about the Employee. ")
public class EmployeeCreateRequest {
    @ApiModelProperty(notes = "${employee.name}", required = true, example = "thaidh")
    @NotEmpty
    @Size(max = 25)
    private String name;

    @ApiModelProperty(notes = "Employee Birthday", required = true, example = "2019-10-29")
    private Date birthday;

    @ApiModelProperty(notes = "Employee Address")
    private String address;

    @ApiModelProperty(notes = "Employee Email", required = true, example = "thaidoan203@gmail.com")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @NotNull
    private String email;

    @NotNull
    private EmployeeStatus status;
}
