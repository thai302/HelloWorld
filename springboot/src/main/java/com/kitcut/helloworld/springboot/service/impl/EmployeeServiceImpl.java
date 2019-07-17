package com.kitcut.helloworld.springboot.service.impl;

import com.kitcut.helloworld.springboot.dto.EmployeeDto;
import com.kitcut.helloworld.springboot.entity.Employee;
import com.kitcut.helloworld.springboot.repository.EmployeeRepository;
import com.kitcut.helloworld.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
//        return employeeRepository.findOne(id);
        return null;
    }

    @Override
    @Transactional
    public void update(Long id, EmployeeDto dto) {
        try {
            Employee employee = findById(id);
            employee.setFirstName(dto.getFirstName());
            employeeRepository.save(employee);

            int i = 0;
            int k = 1 / 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
