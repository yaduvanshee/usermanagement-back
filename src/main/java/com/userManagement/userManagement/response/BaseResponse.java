package com.userManagement.userManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
  private String message;
  private Boolean success;

  public BaseResponse(final String message,final Boolean success){
    this.message=message;
    this.success=success;
  }

  public BaseResponse(final String message){
    this.message=message;
  }

}
