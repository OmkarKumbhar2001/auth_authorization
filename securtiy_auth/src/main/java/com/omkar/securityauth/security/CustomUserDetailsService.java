package com.omkar.securityauth.security;

import com.omkar.securityauth.entity.*;
import com.omkar.securityauth.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {

        User user = userRepository
                .findByEmailAndDeletedAtIsNull(
                        email.trim().toLowerCase(Locale.ROOT)
                )
                .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid credentials")
                );

        Set<GrantedAuthority> authorities = new LinkedHashSet<>();

        // 1. Load active user-role assignments
        var userRoles = userRoleRepository
                .findByUser_IdAndDeletedAtIsNull(user.getId());

        for (UserRole userRole : userRoles) {

            Role role = userRole.getRole();

            // Ignore deleted roles
            if (role.getDeletedAt() != null) {
                continue;
            }

            // 2. Add role authority
            authorities.add(
                    new SimpleGrantedAuthority(
                            "ROLE_" + role.getRoleName().name()
                    )
            );

            // 3. Load active permissions for this role
            var rolePermissions = rolePermissionRepository
                    .findByRole_IdAndDeletedAtIsNull(role.getId());

            for (RolePermission rolePermission : rolePermissions) {

                Permission permission = rolePermission.getPermission();

                if (permission.getDeletedAt() != null) {
                    continue;
                }

                // 4. Add permission authority
                authorities.add(
                        new SimpleGrantedAuthority(
                                permission.getPermissionName().name()
                        )
                );
            }
        }

        return new CustomUserDetails(user, authorities);
    }
}
