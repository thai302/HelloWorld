package com.ecorau.vbnapi.core.service;

import com.ecorau.vbnapi.core.entity.TokenEntity;

public interface TokenService extends BaseService<TokenEntity, Long> {
    TokenEntity findByToken(String token);
}

