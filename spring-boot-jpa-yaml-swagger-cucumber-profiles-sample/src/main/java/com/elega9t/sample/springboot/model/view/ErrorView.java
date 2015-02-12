package com.elega9t.sample.springboot.model.view;

import com.elega9t.sample.springboot.model.view.codec.JodaDateTimeDeserializer;
import com.elega9t.sample.springboot.model.view.codec.JodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.joda.time.DateTime;

public class ErrorView {

  @JsonSerialize(using = JodaDateTimeSerializer.class)
  @JsonDeserialize(using = JodaDateTimeDeserializer.class)
  private DateTime time;
  
  private String message;

  public ErrorView() {
  }

  public ErrorView(String message) {
    this.time = DateTime.now();
    this.message = message;
  }

  public DateTime getTime() {
    return time;
  }

  public void setTime(DateTime time) {
    this.time = time;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
}
