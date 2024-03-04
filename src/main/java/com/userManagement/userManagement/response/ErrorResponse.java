package com.userManagement.userManagement.response;

import java.util.List;
import lombok.Getter;

@Getter
public class ErrorResponse extends BaseResponse{
  private String errorCode;
  private List<String> errorList;
  public ErrorResponse(String message,String errorCode, Boolean success){
    super(message,success);
    this.errorCode = errorCode;
  }

  public ErrorResponse(String message, Boolean success ,List<String> errorList){
    super(message, success);
    this.errorList=errorList;

  }

  public ErrorResponse(String message, String description) {
    super(message);
  }
}
