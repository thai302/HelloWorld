package com.kitcut.helloworld.baserestapi.repository.impl;

import com.kitcut.helloworld.baserestapi.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);
}
