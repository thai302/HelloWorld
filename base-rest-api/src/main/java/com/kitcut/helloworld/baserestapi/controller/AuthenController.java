package com.kitcut.helloworld.baserestapi.controller;

import com.kitcut.helloworld.baserestapi.annotation.Permission;
import com.kitcut.helloworld.baserestapi.dto.request.authen.LoginRequest;
import com.kitcut.helloworld.baserestapi.dto.response.employee.BaseResponse;
import com.kitcut.helloworld.baserestapi.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authen")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginRequest loginRequest) {
        String response = authenService.login(loginRequest);
        return BaseResponse.ok(response);
    }

    @PostMapping("/logout")
    @Permission("")
    public Object logout() {
        authenService.logout();
        return BaseResponse.ok(null);
    }
}
