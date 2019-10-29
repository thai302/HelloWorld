package com.kitcut.helloworld.baserestapi.dto.request.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@ApiModel(description = "All details about the Employee. ")
public class EmployeeCreateRequest {
    @Value("${employee.name}")
    private String name1;

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
}
