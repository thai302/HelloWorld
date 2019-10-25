package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.dto.response.employee.BaseResponse;
import com.kitcut.helloworld.baserestapi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @GetMapping("/{id}")
    public Object getDetail(@PathVariable ID id) {
        E response = service.findById(id);
        return BaseResponse.ok(response);
    }

    @GetMapping("/get-all")
    public Object getAll() {
        List<E> response = service.findAll();
        return BaseResponse.ok(response);
    }
}
