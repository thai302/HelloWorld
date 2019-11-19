package com.ecorau.vbnapi.core.service.impl;

import com.ecorau.vbnapi.core.repository.impl.TokenRepo;
import com.ecorau.vbnapi.core.service.TokenService;
import com.ecorau.vbnapi.core.entity.TokenEntity;
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
