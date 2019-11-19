package com.ecorau.vbnapi.core.service.impl;

import com.ecorau.vbnapi.core.repository.impl.PermissionUserRepo;
import com.ecorau.vbnapi.core.entity.PermissionUserEntity;
import com.ecorau.vbnapi.core.service.PermissionUserService;
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
