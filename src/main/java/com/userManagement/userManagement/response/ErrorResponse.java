package com.userManagement.userManagement.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends BaseResponse{
  private final String errorCode;
  public ErrorResponse(String message,String errorCode, Boolean success){
    super(message,success);
    this.errorCode = errorCode;
  }
}
