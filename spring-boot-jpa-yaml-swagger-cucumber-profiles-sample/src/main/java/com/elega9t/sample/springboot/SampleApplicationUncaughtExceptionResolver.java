package com.elega9t.sample.springboot;

import com.elega9t.sample.springboot.model.view.ErrorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
class SampleApplicationUncaughtExceptionResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleApplicationUncaughtExceptionResolver.class);

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ErrorView handleException(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
    if(e instanceof MethodArgumentNotValidException) {
      throw e;
    }

    LOGGER.error("Exception occurred. Request: " + req.getMethod() + " " + req.getRequestURL(), e);
    if (!(e instanceof SampleApplicationException)) {
      e = new SampleApplicationException(ErrorCode.UNKNOWN);
    }
    SampleApplicationException ex = (SampleApplicationException) e;
    res.setStatus(ex.getHttpStatus());
    return new ErrorView(String.format("%s: %s", ex.getErrorCode(), e.getMessage()));
  }

}
