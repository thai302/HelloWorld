package com.kitcut.helloworld.springboot.controller;

import com.kitcut.helloworld.springboot.dto.EmployeeDto;
import com.kitcut.helloworld.springboot.entity.Employee;
import com.kitcut.helloworld.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    public EmployeeDto create(@RequestBody @Valid EmployeeDto dto) {
        return dto;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid EmployeeDto dto) {
        employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }

    @GetMapping("/{id}")
    public Employee getDetail(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/get-all")
    @CrossOrigin
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/test-jsonp")
    public String testJsonp(@RequestParam("callback") String callback,
                            @RequestParam("id") String id
    ) {
        return callback + "({\"id\":1, \"name\":\"name1\"})";

    }
}
