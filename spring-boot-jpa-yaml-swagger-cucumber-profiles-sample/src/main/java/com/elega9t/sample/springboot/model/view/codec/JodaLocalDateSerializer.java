package com.elega9t.sample.springboot.model.view.codec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaLocalDateSerializer extends JsonSerializer<LocalDate> {

  private final static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

  @Override
  public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    jgen.writeString(format.print(value));
  }

}
