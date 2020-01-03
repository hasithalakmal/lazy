package com.smile.lazy.exception;

import org.springframework.http.HttpStatus;

public class LazyException extends Exception {

  private static final long serialVersionUID = 3078289635627866758L;

  private final HttpStatus httpStatusCode;
  private final String errorCode;

  public LazyException(HttpStatus httpStatusCode, String errorCode, String message, Throwable t) {
    super(message, t);
    this.errorCode = errorCode;
    this.httpStatusCode = httpStatusCode;
  }

  public LazyException(HttpStatus httpStatusCode, String errorCode, String message) {
    this(httpStatusCode, errorCode, message, null);
  }

  public String getErrorCode() {
    return errorCode;
  }

  public HttpStatus getHttpStatusCode() {
    return httpStatusCode;
  }
}
