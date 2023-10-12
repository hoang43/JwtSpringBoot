package com.example.myproject.domain.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

  public BaseException(String message) {
    super(message);
  }
}