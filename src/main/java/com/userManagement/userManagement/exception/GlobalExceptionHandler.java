package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(UserManagementException.class)
  public ResponseEntity<ErrorResponse> handleUserManagementException(UserManagementException userManagementException){
    log.error("Exception : {0}" + userManagementException);
    return new ResponseEntity<>(userManagementException.getErrorResponse(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException){
    log.error("User not found : {0}" + userNotFoundException);
    return new ResponseEntity<>(userNotFoundException.getErrorResponse(),HttpStatus.NOT_FOUND);
  }

}
