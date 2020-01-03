package com.smile.lazy.common;

public class ErrorCodes {

  public static final String UNKNOWN_SERVER_ERROR = "Lazy-10000";
  public static final String NOT_IMPLEMENTED = "Lazy-10010";

  //HTTP method call related exceptions
  public static final String URI_SYNTAX_ERROR = "Lazy-20000";
  public static final String IO_EXCEPTION = "Lazy-20010";

  private ErrorCodes() {
    //This is a private constructor
  }
}
