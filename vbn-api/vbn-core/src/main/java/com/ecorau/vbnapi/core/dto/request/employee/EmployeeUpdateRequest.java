package com.ecorau.vbnapi.core.dto.request.employee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class EmployeeUpdateRequest {
    @NotEmpty
    private String name;
    private Date birthday;
    private String address;
}
