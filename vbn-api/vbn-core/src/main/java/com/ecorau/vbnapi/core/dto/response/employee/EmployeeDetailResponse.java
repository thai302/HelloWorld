package com.ecorau.vbnapi.core.dto.response.employee;

import com.ecorau.vbnapi.core.config.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(description = "Employee detail response")
public class EmployeeDetailResponse extends EmployeeBaseResponse{
    @JsonSerialize(using = JsonDateSerializer.DateTimeJsonSerializer.class)
    private Date cmnCreatedAt;

    @JsonSerialize(using = JsonDateSerializer.DateTimeJsonSerializer.class)
    private Date cmnUpdatedAt;
}
