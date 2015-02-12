package com.elega9t.sample.springboot.model.view.codec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaDateTimeSerializer extends JsonSerializer<DateTime> {

  private final static DateTimeFormatter format =
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

  @Override
  public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    jgen.writeString(format.print(value));
  }

}
