package com.ecorau.vbnapi.core.repository.impl;

import com.ecorau.vbnapi.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
}
