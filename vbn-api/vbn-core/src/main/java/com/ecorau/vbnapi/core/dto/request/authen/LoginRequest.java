package com.ecorau.vbnapi.core.dto.request.authen;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;
}