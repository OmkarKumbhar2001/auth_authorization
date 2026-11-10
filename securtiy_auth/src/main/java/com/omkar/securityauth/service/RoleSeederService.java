
package com.omkar.securityauth.service;

import com.omkar.securityauth.entity.Permission;
import com.omkar.securityauth.entity.Role;
import com.omkar.securityauth.entity.RolePermission;
import com.omkar.securityauth.enums.PermissionName;
import com.omkar.securityauth.enums.RoleName;
import com.omkar.securityauth.repository.PermissionRepository;
import com.omkar.securityauth.repository.RolePermissionRepository;
import com.omkar.securityauth.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleSeederService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Transactional
    public void seed() {

        // 1. Seed roles
        for (RoleName roleName : RoleName.values()) {

            roleRepository.findByRoleNameAndDeletedAtIsNull(roleName)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleName(roleName);
                        return roleRepository.save(role);
                    });
        }

        // 2. Seed permissions
        for (PermissionName permissionName : PermissionName.values()) {

            permissionRepository
                    .findByPermissionNameAndDeletedAtIsNull(permissionName)
                    .orElseGet(() -> {
                        Permission permission = new Permission();
                        permission.setPermissionName(permissionName);
                        return permissionRepository.save(permission);
                    });
        }

        // 3. Configure role-permission mapping
        Map<RoleName, EnumSet<PermissionName>> mappings =
                new EnumMap<>(RoleName.class);

        mappings.put(
                RoleName.ADMIN,
                EnumSet.allOf(PermissionName.class)
        );

        mappings.put(
                RoleName.MANAGER,
                EnumSet.of(
                        PermissionName.USER_READ,
                        PermissionName.USER_UPDATE,
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE
                )
        );

        mappings.put(
                RoleName.USER,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE
                )
        );

        // 4. Seed role permissions
        mappings.forEach((roleName, permissions) -> {

            Role role = roleRepository
                    .findByRoleNameAndDeletedAtIsNull(roleName)
                    .orElseThrow();

            for (PermissionName permissionName : permissions) {

                Permission permission = permissionRepository
                        .findByPermissionNameAndDeletedAtIsNull(permissionName)
                        .orElseThrow();

                boolean exists =
                        rolePermissionRepository
                                .existsByRoleIdAndPermissionIdAndDeletedAtIsNull(
                                        role.getId(),
                                        permission.getId()
                                );

                if (!exists) {

                    RolePermission rolePermission =
                            new RolePermission();

                    rolePermission.setRole(role);
                    rolePermission.setPermission(permission);

                    rolePermissionRepository.save(rolePermission);
                }
            }
        });
    }
}

