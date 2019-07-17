package com.kitcut.helloworld.springboot.controller;

import com.kitcut.helloworld.springboot.dto.EmployeeDto;
import com.kitcut.helloworld.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getDetail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.getForEntity("http://localhost:8081/kitcut/api/employee/716870", Employee.class, "");
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List<EmployeeDto>> response = restTemplate.exchange(
                "http://localhost:8081/kitcut/api/employee/get-all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>(){});
//        List<Employee> employees = response.getBody();
        return response;
    }
}
