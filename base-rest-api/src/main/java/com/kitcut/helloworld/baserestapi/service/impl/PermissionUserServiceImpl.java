package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.entity.PermissionUserEntity;
import com.kitcut.helloworld.baserestapi.repository.impl.PermissionUserRepo;
import com.kitcut.helloworld.baserestapi.service.PermissionUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionUserServiceImpl extends BaseServiceImpl<PermissionUserEntity, Long, PermissionUserRepo> implements PermissionUserService {
    @Override
    public PermissionUserEntity findByUserIdAndPermissionId(Long userId, Long permissionId) {
        return repo.findByUserIdAndPermissionId(userId, permissionId);
    }

    @Override
    public PermissionUserEntity findByUserIdAndPermissionName(Long userId, String permissionName) {
        return repo.findByUserIdAndPermissionName(userId, permissionName);
    }
}
