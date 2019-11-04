package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.*;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService extends BaseService<EmployeeEntity, Long> {
    EmployeeCreateResponse create(EmployeeCreateRequest request);

    EmployeeUpdateResponse update(Long id, EmployeeUpdateRequest request);

    EmployeeDetailResponse getDetail(Long id);

    List<EmployeeListResponse> getAll();

    Page<EmployeePageResponse> getAllPaging(Pageable pageable);

    BasePageResponse<EmployeePageResponse> getAllPagingCustom(Pageable pageable);
}

