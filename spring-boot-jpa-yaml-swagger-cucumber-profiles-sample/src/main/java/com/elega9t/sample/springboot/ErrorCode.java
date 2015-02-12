package com.elega9t.sample.springboot;

public enum ErrorCode {

    UNKNOWN("00000", 500, "An unknown error occurred. Please contact support."),
    USER_NOT_FOUND("00001", 404, "The requested user was not found!"),
    AUTHORITY_NOT_FOUND("00002", 404, "The requested authority was not found!");

    public final String errorCode;
    public final int httpStatus;
    public final String message;

    ErrorCode(String errorCode, int httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
