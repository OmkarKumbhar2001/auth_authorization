package com.omkar.securityauth.repository;

import com.omkar.securityauth.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository
        extends JpaRepository<RolePermission, Long> {

    List<RolePermission> findByRoleIdAndDeletedAtIsNull(Long roleId);

    boolean existsByRoleIdAndPermissionIdAndDeletedAtIsNull(
            Long roleId,
            Long permissionId
    );
}