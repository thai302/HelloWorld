package com.ecorau.vbnapi.core.service;

import com.ecorau.vbnapi.core.dto.request.authen.LoginRequest;

public interface AuthenService {
    String login(LoginRequest loginRequest);

    void logout();
}
