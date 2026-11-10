
package com.omkar.securityauth.enums;

public enum PermissionName {

    // ==============================
    // PLATFORM MANAGEMENT
    // ==============================

    PLATFORM_COMPANY_READ,
    PLATFORM_COMPANY_CREATE,
    PLATFORM_COMPANY_UPDATE,
    PLATFORM_COMPANY_DELETE,
    PLATFORM_COMPANY_SUSPEND,

    // ==============================
    // COMPANY MANAGEMENT
    // ==============================

    COMPANY_READ,
    COMPANY_UPDATE,
    COMPANY_DELETE,
    COMPANY_SETTINGS_MANAGE,

    // ==============================
    // USER MANAGEMENT
    // ==============================

    USER_READ,
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
    USER_INVITE,
    USER_ACTIVATE,
    USER_DEACTIVATE,

    // ==============================
    // PROFILE MANAGEMENT
    // ==============================

    PROFILE_READ,
    PROFILE_UPDATE,

    // ==============================
    // ROLE MANAGEMENT
    // ==============================

    ROLE_READ,
    ROLE_CREATE,
    ROLE_UPDATE,
    ROLE_DELETE,
    ROLE_ASSIGN,

    // ==============================
    // PERMISSION MANAGEMENT
    // ==============================

    PERMISSION_READ,
    PERMISSION_ASSIGN,

    // ==============================
    // CLIENT MANAGEMENT
    // ==============================

    CLIENT_READ,
    CLIENT_CREATE,
    CLIENT_UPDATE,
    CLIENT_DELETE,
    CLIENT_INVITE,

    // ==============================
    // PROJECT MANAGEMENT
    // ==============================

    PROJECT_READ,
    PROJECT_CREATE,
    PROJECT_UPDATE,
    PROJECT_DELETE,
    PROJECT_ARCHIVE,
    PROJECT_ASSIGN,

    // ==============================
    // TASK MANAGEMENT
    // ==============================

    TASK_READ,
    TASK_CREATE,
    TASK_UPDATE,
    TASK_DELETE,
    TASK_ASSIGN,
    TASK_STATUS_UPDATE,
    TASK_PRIORITY_UPDATE,
    TASK_DUE_DATE_UPDATE,

    // ==============================
    // COMMENTS
    // ==============================

    COMMENT_READ,
    COMMENT_CREATE,
    COMMENT_UPDATE,
    COMMENT_DELETE,

    // ==============================
    // ATTACHMENTS
    // ==============================

    ATTACHMENT_READ,
    ATTACHMENT_UPLOAD,
    ATTACHMENT_DELETE,

    // ==============================
    // SPRINT MANAGEMENT
    // ==============================

    SPRINT_READ,
    SPRINT_CREATE,
    SPRINT_UPDATE,
    SPRINT_DELETE,
    SPRINT_MANAGE,

    // ==============================
    // REPORTS AND DASHBOARDS
    // ==============================

    REPORT_READ,
    REPORT_CREATE,
    REPORT_EXPORT,

    DASHBOARD_READ,
    DASHBOARD_CREATE,
    DASHBOARD_UPDATE,
    DASHBOARD_DELETE,

    // ==============================
    // TIME TRACKING
    // ==============================

    TIME_TRACKING_READ,
    TIME_TRACKING_CREATE,
    TIME_TRACKING_UPDATE,
    TIME_TRACKING_DELETE,

    // ==============================
    // AUDIT LOGS
    // ==============================

    AUDIT_LOG_READ
}
