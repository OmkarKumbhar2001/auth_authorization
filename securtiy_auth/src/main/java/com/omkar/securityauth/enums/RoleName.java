package com.omkar.securityauth.enums;


public enum RoleName {

    // Platform-level roles
    SUPER_ADMIN,

    // Company-level roles
    COMPANY_OWNER,
    COMPANY_ADMIN,

    // Project and team management
    PROJECT_MANAGER,
    TEAM_LEAD,

    // Project members
    DEVELOPER,
    CONTRIBUTOR,

    // External access
    CLIENT,
    VIEWER,

    // General user
    USER
}
