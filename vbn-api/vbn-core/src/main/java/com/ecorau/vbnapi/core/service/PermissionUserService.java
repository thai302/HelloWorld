package com.ecorau.vbnapi.core.service;

import com.ecorau.vbnapi.core.entity.PermissionUserEntity;

public interface PermissionUserService extends BaseService<PermissionUserEntity, Long> {
    PermissionUserEntity findByUserIdAndPermissionId(Long userId, Long permissionId);

    PermissionUserEntity findByUserIdAndPermissionName(Long userId, String permissionName);

}

