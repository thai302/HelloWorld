package com.ecorau.vbnapi.core.service;

import com.ecorau.vbnapi.core.dto.request.employee.EmployeeCreateRequest;
import com.ecorau.vbnapi.core.dto.request.employee.EmployeeUpdateRequest;
import com.ecorau.vbnapi.core.dto.response.employee.*;
import com.ecorau.vbnapi.core.entity.EmployeeEntity;
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

