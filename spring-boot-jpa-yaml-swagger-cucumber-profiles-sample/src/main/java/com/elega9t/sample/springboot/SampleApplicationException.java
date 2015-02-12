package com.elega9t.sample.springboot;

public class SampleApplicationException extends RuntimeException {

  private final String errorCode;
  private final int httpStatus;

  public SampleApplicationException(ErrorCode errorCode) {
    this(errorCode.errorCode, errorCode.httpStatus, errorCode.message);
  }

  public SampleApplicationException(String errorCode, int httpStatus, String message) {
    super(message);
    this.errorCode = errorCode;
    this.httpStatus = httpStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

}
