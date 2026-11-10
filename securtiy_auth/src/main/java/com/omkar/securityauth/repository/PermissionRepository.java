
package com.omkar.securityauth.repository;

import com.omkar.securityauth.entity.Permission;
import com.omkar.securityauth.enums.PermissionName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository
        extends JpaRepository<Permission, Long> {

    Optional<Permission> findByPermissionNameAndDeletedAtIsNull(
            PermissionName permissionName
    );

    boolean existsByPermissionName(PermissionName permissionName);
}
