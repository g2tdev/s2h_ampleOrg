package com.ampleexchange.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class MyException extends BaseException {
  public MyException(String message) {
      super(message);
  }
}