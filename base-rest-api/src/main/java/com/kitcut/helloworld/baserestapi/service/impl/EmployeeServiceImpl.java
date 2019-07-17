package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeCreateResponse;
import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeUpdateResponse;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import com.kitcut.helloworld.baserestapi.repository.impl.EmployeeRepo;
import com.kitcut.helloworld.baserestapi.service.EmployeeService;
import com.kitcut.helloworld.baserestapi.util.MappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeEntity, Long, EmployeeRepo> implements EmployeeService {
    @Override
    public EmployeeCreateResponse create(EmployeeCreateRequest request) {
        //create
        EmployeeEntity employeeEntity = new EmployeeEntity();
        MappingUtils.mapping(request, employeeEntity);
        save(employeeEntity);

        //return response
        EmployeeCreateResponse response = new EmployeeCreateResponse();
        MappingUtils.mapping(employeeEntity, response);
        return response;
    }

    @Override
    public EmployeeUpdateResponse update(Long id, EmployeeUpdateRequest request) {
        //check exist by id
        EmployeeEntity employeeEntity = findById(id);

        //update
        MappingUtils.mapping(request, employeeEntity);
        save(employeeEntity);

        //return response
        EmployeeUpdateResponse response = new EmployeeUpdateResponse();
        MappingUtils.mapping(employeeEntity, response);
        return response;
    }
}
