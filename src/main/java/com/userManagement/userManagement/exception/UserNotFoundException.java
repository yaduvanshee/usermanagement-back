package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;
import lombok.Getter;

public class UserNotFoundException extends UserManagementException {

  public UserNotFoundException(ErrorResponse errorResponse) {
    super(errorResponse);
  }
}
