package com.kitcut.helloworld.springboot.service;

import com.kitcut.helloworld.springboot.dto.EmployeeDto;
import com.kitcut.helloworld.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long id);

    void update(Long id, EmployeeDto dto);
}
