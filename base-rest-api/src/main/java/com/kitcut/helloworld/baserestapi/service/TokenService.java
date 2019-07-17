package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.entity.TokenEntity;
import com.kitcut.helloworld.baserestapi.service.BaseService;

public interface TokenService extends BaseService<TokenEntity, Long> {
    TokenEntity findByToken(String token);
}

