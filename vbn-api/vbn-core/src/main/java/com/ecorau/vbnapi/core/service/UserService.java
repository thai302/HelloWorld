package com.ecorau.vbnapi.core.service;

import com.ecorau.vbnapi.core.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, Long> {
    UserEntity findByName(String userName);
}
