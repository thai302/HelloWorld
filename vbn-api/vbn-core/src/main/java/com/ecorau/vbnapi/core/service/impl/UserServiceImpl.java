package com.ecorau.vbnapi.core.service.impl;

import com.ecorau.vbnapi.core.repository.impl.UserRepo;
import com.ecorau.vbnapi.core.service.UserService;
import com.ecorau.vbnapi.core.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long, UserRepo> implements UserService {
    @Override
    public UserEntity findByName(String userName) {
        return repo.findByName(userName);
    }
}
