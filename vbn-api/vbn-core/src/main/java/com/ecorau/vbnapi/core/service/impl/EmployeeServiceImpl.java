package com.ecorau.vbnapi.core.service.impl;

import com.ecorau.vbnapi.core.dto.response.employee.*;
import com.ecorau.vbnapi.core.repository.impl.EmployeeRepo;
import com.ecorau.vbnapi.core.service.EmployeeService;
import com.ecorau.vbnapi.core.dto.request.employee.EmployeeCreateRequest;
import com.ecorau.vbnapi.core.dto.request.employee.EmployeeUpdateRequest;
import com.ecorau.vbnapi.core.entity.EmployeeEntity;
import com.ecorau.vbnapi.core.util.MappingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeEntity, Long, EmployeeRepo> implements EmployeeService {
    @Override
    public EmployeeCreateResponse create(EmployeeCreateRequest request) {
        //create
        EmployeeEntity employeeEntity = MappingUtils.mappingObject(request, EmployeeEntity.class);
        save(employeeEntity);

        //mapping response
        EmployeeCreateResponse response = MappingUtils.mappingObject(employeeEntity, EmployeeCreateResponse.class);

        return response;
    }

    @Override
    public EmployeeUpdateResponse update(Long id, EmployeeUpdateRequest request) {
        //check exist by id
        EmployeeEntity employeeEntity = findById(id);

        //update
        MappingUtils.mappingObject(request, employeeEntity);
        save(employeeEntity);

        //mapping response
        EmployeeUpdateResponse response = MappingUtils.mappingObject(employeeEntity, EmployeeUpdateResponse.class);

        return response;
    }

    @Override
    public EmployeeDetailResponse getDetail(Long id) {
        //check exist by id
        EmployeeEntity employeeEntity = findById(id);

        //mapping response
        EmployeeDetailResponse response = MappingUtils.mappingObject(employeeEntity, EmployeeDetailResponse.class);

        return response;
    }

    @Override
    public List<EmployeeListResponse> getAll() {
        //get all
        List<EmployeeEntity> employeeEntityList = findAll();

        //mapping response
        List<EmployeeListResponse> response = MappingUtils.mappingListObject(employeeEntityList, EmployeeListResponse.class);

        return response;
    }

    @Override
    public Page<EmployeePageResponse> getAllPaging(Pageable pageable) {
        //get page
        Page<EmployeeEntity> employeeEntityPage = findAll(pageable);

        //mapping response
        Page<EmployeePageResponse> response = MappingUtils.mappingPage(employeeEntityPage, EmployeePageResponse.class);

        return response;
    }

    @Override
    public BasePageResponse<EmployeePageResponse> getAllPagingCustom(Pageable pageable) {
        //get page
        Page<EmployeeEntity> employeeEntityPage = findAll(pageable);

        //mapping response
        BasePageResponse<EmployeePageResponse> response = MappingUtils.mappingPageCustom(employeeEntityPage, EmployeePageResponse.class);

        return response;
    }
}
