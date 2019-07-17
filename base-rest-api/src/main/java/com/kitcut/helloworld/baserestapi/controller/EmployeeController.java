package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.annotation.Permission;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.BaseResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeCreateResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeUpdateResponse;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import com.kitcut.helloworld.baserestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends BaseController<EmployeeEntity, Long, EmployeeService> {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    @Permission("employee-create")
    public Object create(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeCreateResponse response = employeeService.create(request);
        return BaseResponse.ok(response);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateRequest request) {
        EmployeeUpdateResponse response = employeeService.update(id, request);
        return BaseResponse.ok(response);
    }
}
