package com.kitcut.helloworld.baserestapi.repository.impl;

import com.kitcut.helloworld.baserestapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
}
