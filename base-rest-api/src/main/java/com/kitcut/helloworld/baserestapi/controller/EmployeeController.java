package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeCreateRequest;
import com.kitcut.helloworld.baserestapi.dto.request.employee.EmployeeUpdateRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.*;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import com.kitcut.helloworld.baserestapi.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController extends BaseController<EmployeeEntity, Long, EmployeeService> {

    @PostMapping("")
//    @Permission("employee-create")
    public BaseResponse<EmployeeCreateResponse> create(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeCreateResponse response = service.create(request);
        return BaseResponse.ok(response);
    }

    @PutMapping("/{id}")
    public BaseResponse<EmployeeUpdateResponse> update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateRequest request) {
        EmployeeUpdateResponse response = service.update(id, request);
        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse<List<EmployeeListResponse>> getAll() {
        List<EmployeeListResponse> response = service.getAll();
        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse<EmployeeDetailResponse> getDetail(
            @ApiParam(value = "Employee Id", required = true) @PathVariable Long id) {
        EmployeeDetailResponse response = service.getDetail(id);
        return BaseResponse.ok(response);
    }

    @GetMapping("get-all-paging")
    public BaseResponse<Page<EmployeePageResponse>> getAllPaging(Pageable pageable){
        Page<EmployeePageResponse> response = service.getAllPaging(pageable);
        return BaseResponse.ok(response);
    }

    @GetMapping("get-all-paging-custom")
    public BaseResponse<BasePageResponse<EmployeePageResponse>> getAllPagingCustom(Pageable pageable){
        BasePageResponse<EmployeePageResponse> response = service.getAllPagingCustom(pageable);
        return BaseResponse.ok(response);
    }
}
