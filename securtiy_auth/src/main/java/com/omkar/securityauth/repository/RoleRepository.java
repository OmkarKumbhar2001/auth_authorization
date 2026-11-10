
package com.omkar.securityauth.repository;

import com.omkar.securityauth.entity.Role;
import com.omkar.securityauth.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleNameAndDeletedAtIsNull(RoleName roleName);

    boolean existsByRoleNameAndDeletedAtIsNull(RoleName roleName);
}
