package com.elega9t.sample.springboot.model.view.codec;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.io.IOException;

public class JodaLocalDateDeserializer extends JsonDeserializer<LocalDate> {

  private final static DateTimeFormatter formatter =
      new DateTimeFormatterBuilder().append(null, getParsers()).toFormatter();

  private static DateTimeParser[] getParsers() {
    return new DateTimeParser[]{
        DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").getParser()};
  }

  @Override
  public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return formatter.parseLocalDate(jp.getValueAsString());
  }

}
