package com.kitcut.helloworld.baserestapi.service;

import com.kitcut.helloworld.baserestapi.entity.PermissionUserEntity;

public interface PermissionUserService extends BaseService<PermissionUserEntity, Long> {
    PermissionUserEntity findByUserIdAndPermissionId(Long userId, Long permissionId);

    PermissionUserEntity findByUserIdAndPermissionName(Long userId, String permissionName);

}

