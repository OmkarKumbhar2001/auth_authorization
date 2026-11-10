
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

        // 1. Seed all roles
        for (RoleName roleName : RoleName.values()) {

            roleRepository.findByRoleNameAndDeletedAtIsNull(roleName)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleName(roleName);
                        return roleRepository.save(role);
                    });
        }

        // 2. Seed all permissions
        for (PermissionName permissionName : PermissionName.values()) {

            permissionRepository
                    .findByPermissionNameAndDeletedAtIsNull(permissionName)
                    .orElseGet(() -> {
                        Permission permission = new Permission();
                        permission.setPermissionName(permissionName);
                        return permissionRepository.save(permission);
                    });
        }

        // 3. Configure role-permission mappings
        Map<RoleName, EnumSet<PermissionName>> mappings =
                new EnumMap<>(RoleName.class);

        // Platform super admin
        mappings.put(
                RoleName.SUPER_ADMIN,
                EnumSet.allOf(PermissionName.class)
        );

        // Company owner
        mappings.put(
                RoleName.COMPANY_OWNER,
                EnumSet.complementOf(
                        EnumSet.copyOf(
                                EnumSet.of(
                                        PermissionName.PLATFORM_COMPANY_CREATE
                                )
                        )
                )
        );

        // Company admin
        EnumSet<PermissionName> companyAdmin =
                EnumSet.allOf(PermissionName.class);

        companyAdmin.removeAll(EnumSet.of(
                PermissionName.PLATFORM_COMPANY_READ,
                PermissionName.PLATFORM_COMPANY_CREATE,
                PermissionName.PLATFORM_COMPANY_UPDATE,
                PermissionName.PLATFORM_COMPANY_DELETE,
                PermissionName.PLATFORM_COMPANY_SUSPEND,
                PermissionName.COMPANY_DELETE
        ));

        mappings.put(RoleName.COMPANY_ADMIN, companyAdmin);

        // Project manager
        mappings.put(
                RoleName.PROJECT_MANAGER,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE,

                        PermissionName.USER_READ,

                        PermissionName.CLIENT_READ,

                        PermissionName.PROJECT_READ,
                        PermissionName.PROJECT_CREATE,
                        PermissionName.PROJECT_UPDATE,
                        PermissionName.PROJECT_ASSIGN,

                        PermissionName.TASK_READ,
                        PermissionName.TASK_CREATE,
                        PermissionName.TASK_UPDATE,
                        PermissionName.TASK_DELETE,
                        PermissionName.TASK_ASSIGN,
                        PermissionName.TASK_STATUS_UPDATE,
                        PermissionName.TASK_PRIORITY_UPDATE,
                        PermissionName.TASK_DUE_DATE_UPDATE,

                        PermissionName.COMMENT_READ,
                        PermissionName.COMMENT_CREATE,
                        PermissionName.COMMENT_UPDATE,
                        PermissionName.COMMENT_DELETE,

                        PermissionName.ATTACHMENT_READ,
                        PermissionName.ATTACHMENT_UPLOAD,
                        PermissionName.ATTACHMENT_DELETE,

                        PermissionName.SPRINT_READ,
                        PermissionName.SPRINT_CREATE,
                        PermissionName.SPRINT_UPDATE,
                        PermissionName.SPRINT_MANAGE,

                        PermissionName.REPORT_READ,
                        PermissionName.REPORT_CREATE,
                        PermissionName.REPORT_EXPORT,

                        PermissionName.DASHBOARD_READ,
                        PermissionName.DASHBOARD_CREATE,
                        PermissionName.DASHBOARD_UPDATE,

                        PermissionName.TIME_TRACKING_READ,
                        PermissionName.TIME_TRACKING_CREATE,
                        PermissionName.TIME_TRACKING_UPDATE,

                        PermissionName.AUDIT_LOG_READ
                )
        );

        // Team lead
        mappings.put(
                RoleName.TEAM_LEAD,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE,

                        PermissionName.USER_READ,

                        PermissionName.PROJECT_READ,

                        PermissionName.TASK_READ,
                        PermissionName.TASK_CREATE,
                        PermissionName.TASK_UPDATE,
                        PermissionName.TASK_ASSIGN,
                        PermissionName.TASK_STATUS_UPDATE,
                        PermissionName.TASK_PRIORITY_UPDATE,

                        PermissionName.COMMENT_READ,
                        PermissionName.COMMENT_CREATE,
                        PermissionName.COMMENT_UPDATE,

                        PermissionName.ATTACHMENT_READ,
                        PermissionName.ATTACHMENT_UPLOAD,

                        PermissionName.SPRINT_READ,
                        PermissionName.SPRINT_CREATE,
                        PermissionName.SPRINT_UPDATE,

                        PermissionName.REPORT_READ,
                        PermissionName.DASHBOARD_READ,

                        PermissionName.TIME_TRACKING_READ,
                        PermissionName.TIME_TRACKING_CREATE,
                        PermissionName.TIME_TRACKING_UPDATE
                )
        );

        // Developer
        mappings.put(
                RoleName.DEVELOPER,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE,

                        PermissionName.PROJECT_READ,

                        PermissionName.TASK_READ,
                        PermissionName.TASK_UPDATE,
                        PermissionName.TASK_STATUS_UPDATE,

                        PermissionName.COMMENT_READ,
                        PermissionName.COMMENT_CREATE,
                        PermissionName.COMMENT_UPDATE,

                        PermissionName.ATTACHMENT_READ,
                        PermissionName.ATTACHMENT_UPLOAD,

                        PermissionName.SPRINT_READ,

                        PermissionName.TIME_TRACKING_READ,
                        PermissionName.TIME_TRACKING_CREATE,
                        PermissionName.TIME_TRACKING_UPDATE,

                        PermissionName.DASHBOARD_READ
                )
        );

        // Contributor
        mappings.put(
                RoleName.CONTRIBUTOR,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE,

                        PermissionName.PROJECT_READ,

                        PermissionName.TASK_READ,
                        PermissionName.TASK_UPDATE,
                        PermissionName.TASK_STATUS_UPDATE,

                        PermissionName.COMMENT_READ,
                        PermissionName.COMMENT_CREATE,

                        PermissionName.ATTACHMENT_READ,
                        PermissionName.ATTACHMENT_UPLOAD,

                        PermissionName.DASHBOARD_READ
                )
        );

        // Client
        mappings.put(
                RoleName.CLIENT,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE,

                        PermissionName.CLIENT_READ,

                        PermissionName.PROJECT_READ,

                        PermissionName.TASK_READ,

                        PermissionName.COMMENT_READ,
                        PermissionName.COMMENT_CREATE,

                        PermissionName.ATTACHMENT_READ,
                        PermissionName.ATTACHMENT_UPLOAD,

                        PermissionName.DASHBOARD_READ
                )
        );

        // Viewer - read-only access
        mappings.put(
                RoleName.VIEWER,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROJECT_READ,
                        PermissionName.TASK_READ,
                        PermissionName.COMMENT_READ,
                        PermissionName.ATTACHMENT_READ,
                        PermissionName.SPRINT_READ,
                        PermissionName.REPORT_READ,
                        PermissionName.DASHBOARD_READ,
                        PermissionName.TIME_TRACKING_READ
                )
        );

        // Basic user
        mappings.put(
                RoleName.USER,
                EnumSet.of(
                        PermissionName.PROFILE_READ,
                        PermissionName.PROFILE_UPDATE
                )
        );

        // 4. Save role-permission relationships
        mappings.forEach(this::assignPermissions);
    }

    private void assignPermissions(
            RoleName roleName,
            EnumSet<PermissionName> permissions) {

        Role role = roleRepository
                .findByRoleNameAndDeletedAtIsNull(roleName)
                .orElseThrow();

        for (PermissionName permissionName : permissions) {

            Permission permission = permissionRepository
                    .findByPermissionNameAndDeletedAtIsNull(permissionName)
                    .orElseThrow();

            boolean exists = rolePermissionRepository
                    .existsByRoleIdAndPermissionIdAndDeletedAtIsNull(
                            role.getId(),
                            permission.getId()
                    );

            if (!exists) {

                RolePermission rolePermission = new RolePermission();

                rolePermission.setRole(role);
                rolePermission.setPermission(permission);

                rolePermissionRepository.save(rolePermission);
            }
        }
    }
}
