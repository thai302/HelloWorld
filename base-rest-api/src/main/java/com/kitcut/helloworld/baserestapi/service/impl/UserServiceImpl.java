package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.entity.UserEntity;
import com.kitcut.helloworld.baserestapi.repository.impl.UserRepo;
import com.kitcut.helloworld.baserestapi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long, UserRepo> implements UserService {
    @Override
    public UserEntity findByName(String userName) {
        return repo.findByName(userName);
    }
}
