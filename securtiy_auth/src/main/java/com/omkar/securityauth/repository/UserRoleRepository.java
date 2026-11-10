package com.omkar.securityauth.repository;

import com.omkar.securityauth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository
        extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserIdAndDeletedAtIsNull(Long userId);

    boolean existsByUserIdAndRoleIdAndDeletedAtIsNull(
            Long userId,
            Long roleId
    );
}