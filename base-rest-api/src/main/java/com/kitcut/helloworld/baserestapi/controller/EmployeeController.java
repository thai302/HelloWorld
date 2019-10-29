package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.BaseResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeCreateResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeUpdateResponse;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import com.kitcut.helloworld.baserestapi.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController extends BaseController<EmployeeEntity, Long, EmployeeService> {

    @PostMapping("")
//    @Permission("employee-create")
    public BaseResponse<EmployeeCreateResponse> create(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeCreateResponse response = service.create(request);
        return BaseResponse.ok(response);
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateRequest request) {
        EmployeeUpdateResponse response = service.update(id, request);
        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse<List<EmployeeEntity>> getAll() {
        List<EmployeeEntity> response = service.findAll();
        return BaseResponse.ok(response);
    }

    @Override
    public EmployeeEntity getDetail(
            @ApiParam(value = "Employee Id", required = true) @PathVariable Long id) {
        return service.findById(id);
    }
}
