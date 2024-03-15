package com.userManagement.userManagement.errorEnum;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    INVALID_USER_ID("100-01", "Invalid user id"),
    PASSWORD_EXPIRED("100-02", "Password expired"),
    PERMISSION_DENIED("100-03", "Permission denied"),
    INVALID_EMAIL_ID("100-04","User already exist with email id"),
    UNSUPPORTED_ENCODING_ERROR("100-05","unsupported encoding error"),
    WELCOME_EMAIL_ERROR("100-06","Error while sending welcome email.");

    private final String errorCode;
    private final String errorMsg;

    ErrorEnum(final String errorCode, final String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}

