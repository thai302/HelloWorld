package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeCreateResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeUpdateResponse;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;

public interface EmployeeService extends BaseService<EmployeeEntity, Long> {
    EmployeeCreateResponse create(EmployeeCreateRequest request);

    EmployeeUpdateResponse update(Long id, EmployeeUpdateRequest request);
}

