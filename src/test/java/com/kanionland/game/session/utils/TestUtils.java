package com.kanionland.game.session.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

  public static String toJson(Object object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  public static <T> T fromJson(String json, Class<T> valueType) throws JsonProcessingException {
    return objectMapper.readValue(json, valueType);
  }
}