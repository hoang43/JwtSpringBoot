package com.example.myproject.domain.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
  private int code;
  private String message;

  public static ExceptionResponse createFrom(BaseException e) {
    ExceptionResponse response = new ExceptionResponse();
    response.setMessage(e.getMessage());
    return response;
  }

  public static ExceptionResponse createFrom(Exception e) {
    ExceptionResponse response = new ExceptionResponse();
    response.setMessage(e.getMessage());
    return response;
  }
}