package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.entity.TokenEntity;
import com.kitcut.helloworld.baserestapi.repository.impl.TokenRepo;
import com.kitcut.helloworld.baserestapi.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl extends BaseServiceImpl<TokenEntity, Long, TokenRepo> implements TokenService {

    @Override
    public TokenEntity findByToken(String token) {
        return repo.findByToken(token);
    }
}
