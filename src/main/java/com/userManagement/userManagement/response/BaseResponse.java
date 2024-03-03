package com.userManagement.userManagement.response;

import lombok.Getter;

@Getter
public class BaseResponse {
  private String message;
  private Boolean success;

  public BaseResponse(final String message,final Boolean success){
    this.message=message;
    this.success=success;
  }

}
