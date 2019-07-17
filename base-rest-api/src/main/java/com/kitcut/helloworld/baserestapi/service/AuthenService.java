package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.dto.request.authen.LoginRequest;

public interface AuthenService {
    String login(LoginRequest loginRequest);

    void logout();
}
