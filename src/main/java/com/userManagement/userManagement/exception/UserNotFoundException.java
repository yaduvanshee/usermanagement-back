package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;

public class UserNotFoundException extends UserManagementException {

  public UserNotFoundException(ErrorResponse errorResponse) {
    super(errorResponse);
  }
}
