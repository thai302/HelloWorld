package com.ecorau.vbnapi.core.bean;

import com.ecorau.vbnapi.core.entity.TokenEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class UserSession {
    private Long userId;
    private String userName;
    private TokenEntity tokenEntity;
}
