package com.omkar.securityauth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Authentication errors
    INVALID_CREDENTIALS(
            "BE01001",
            "The email or password provided is incorrect."
    ),
    USER_NOT_FOUND(
            "BE01002",
            "No active user was found for the provided identifier."
    ),
    ACCOUNT_DISABLED(
            "BE01003",
            "The user account is disabled or inactive."
    ),
    ACCESS_DENIED(
            "BE01004",
            "The authenticated user does not have sufficient permissions."
    ),
    TOKEN_EXPIRED(
            "BE01005",
            "The authentication token has expired."
    ),
    INVALID_TOKEN(
            "BE01006",
            "The provided authentication token is invalid."
    ),

    // User errors
    USER_ALREADY_EXISTS(
            "BE02001",
            "A user with the provided email address already exists."
    ),
    INVALID_USER_DATA(
            "BE02002",
            "The provided user data is invalid."
    ),

    // Validation errors
    VALIDATION_FAILED(
            "BE03001",
            "One or more request fields failed validation."
    ),
    INVALID_REQUEST_BODY(
            "BE03002",
            "The request body is missing, malformed, or contains invalid JSON."
    ),

    // General errors
    RESOURCE_NOT_FOUND(
            "BE04001",
            "The requested resource could not be found."
    ),
    DATABASE_ERROR(
            "BE05001",
            "A database operation failed unexpectedly."
    ),
    INTERNAL_SERVER_ERROR(
            "BE99999",
            "An unexpected internal server error occurred."
    );

    private final String code;
    private final String message;
}