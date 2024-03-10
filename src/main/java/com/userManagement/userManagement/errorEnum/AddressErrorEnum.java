package com.userManagement.userManagement.errorEnum;

import lombok.Getter;

@Getter
public enum AddressErrorEnum {
    INVALID_ADDRESS_ID("102-01", "Invalid address id"),
    ADDRESS_EMAIL_ERROR("102-02","Got error while sending Address change email");

    private final String errorCode;
    private final String errorMsg;

    AddressErrorEnum(final String errorCode, final String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
