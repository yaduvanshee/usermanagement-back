package com.userManagement.userManagement.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    INVALID_USER_ID("100-01","Invalid user id"),
    INVALID_USER_NAME("100-02","Invalid user name");

    private final String errorCode;
    private final String errorMsg;

    ErrorEnum(final String errorCode, final String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}