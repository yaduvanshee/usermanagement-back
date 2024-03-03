package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;
import lombok.Getter;

@Getter
public class UserManagementException extends Exception{
  private final ErrorResponse errorResponse;

  public UserManagementException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }


}
