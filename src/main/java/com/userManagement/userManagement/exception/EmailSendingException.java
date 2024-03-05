package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;

public class EmailSendingException extends UserManagementException{
    public EmailSendingException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
