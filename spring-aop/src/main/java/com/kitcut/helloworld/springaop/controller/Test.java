package com.kitcut.helloworld.springaop.controller;

import com.kitcut.helloworld.springaop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Test {

    @Autowired
    private TestService testService;

    @GetMapping("/employees")
    public void getAllEmployees() {
        testService.a();
    }
}
