package com.elega9t.sample.springboot.model.view.codec;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaDateTimeDeserializer extends JsonDeserializer<DateTime> {

  private final static DateTimeFormatter formatter =
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

  @Override
  public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return formatter.parseDateTime(jp.getValueAsString());
  }

}
