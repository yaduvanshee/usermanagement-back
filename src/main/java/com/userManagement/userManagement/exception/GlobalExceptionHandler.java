package com.userManagement.userManagement.exception;

import com.userManagement.userManagement.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      ErrorResponse errorResponse = new ErrorResponse(ex.getFieldError().getDefaultMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
    List<String> errors = new ArrayList<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String errorMessage = String.format("%s: %s", violation.getPropertyPath(), violation.getMessage());
      errors.add(errorMessage);
    }
    ErrorResponse errorResponse = new ErrorResponse("Validation failed", false,errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

}
