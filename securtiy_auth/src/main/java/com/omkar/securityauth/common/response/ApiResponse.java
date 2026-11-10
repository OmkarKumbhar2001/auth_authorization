package com.omkar.securityauth.common.response;


public record ApiResponse<T>(
        boolean success,
        String errorCode,
        T data
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                null,
                data
        );
    }

    public static <T> ApiResponse<T> failure(String errorCode) {
        return new ApiResponse<>(
                false,
                errorCode,
                null
        );
    }
}
