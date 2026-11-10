package com.omkar.securityauth.entity;

import com.omkar.securityauth.enums.PermissionName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_name", nullable = false, unique = true, length = 100)
    private PermissionName permissionName;
}
