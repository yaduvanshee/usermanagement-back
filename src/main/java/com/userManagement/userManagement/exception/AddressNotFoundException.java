package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;

public class AddressNotFoundException extends UserManagementException{
    public AddressNotFoundException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
