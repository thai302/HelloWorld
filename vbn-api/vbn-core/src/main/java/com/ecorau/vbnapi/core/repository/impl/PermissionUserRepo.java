package com.ecorau.vbnapi.core.repository.impl;

import com.ecorau.vbnapi.core.entity.PermissionUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionUserRepo extends JpaRepository<PermissionUserEntity, Long> {
    PermissionUserEntity findByUserIdAndPermissionId(Long userId, Long permissionId);

    @Query(value = "" +
            "select * " +
            "from permission_user " +
            "where user_id = :userId " +
            "and permission_id = (select id from permission where name = :permissionName) limit 1"
            , nativeQuery = true)
    PermissionUserEntity findByUserIdAndPermissionName(@Param(value = "userId") Long userId,
                                                       @Param(value = "permissionName") String permissionName);

}
