package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, Long> {
    UserEntity findByName(String userName);
}
