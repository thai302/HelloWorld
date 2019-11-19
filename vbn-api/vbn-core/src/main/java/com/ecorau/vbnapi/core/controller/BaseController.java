package com.ecorau.vbnapi.core.controller;

import com.ecorau.vbnapi.core.dto.response.employee.BaseResponse;
import com.ecorau.vbnapi.core.service.BaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class BaseController<E, ID, S extends BaseService<E, ID>> {
    @Autowired
    protected S service;

//    @PostMapping("")
//    public <REQUEST, RESPONSE> RESPONSE create(@RequestBody @Valid REQUEST request) {
//        return create(request);
//    }
//
//    @PutMapping("/{id}")
//    public void update(@PathVariable Long id, @RequestBody @Valid EmployeeDto dto) {
//        employeeService.update(id, dto);
//    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable ID id) {
        service.deleteById(id);
        return BaseResponse.ok(null);
    }

    @ApiOperation(value = "get detail", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/{id}")
    public Object getDetail(@ApiParam(value = "Employee Id", required = true)
                            @PathVariable ID id) {
        E response = service.findById(id);
        return BaseResponse.ok(response);
    }

    @GetMapping("/get-all")
    public Object getAll() {
        List<E> response = service.findAll();
        return BaseResponse.ok(response);
    }
}
