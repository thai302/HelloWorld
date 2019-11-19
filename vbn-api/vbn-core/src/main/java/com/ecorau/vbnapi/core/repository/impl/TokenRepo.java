package com.ecorau.vbnapi.core.repository.impl;

import com.ecorau.vbnapi.core.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);
}
