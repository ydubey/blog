package com.elega9t.sample.springboot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtilities {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode resourceAsJson(String resource) throws IOException {
        return mapper.readTree(TestUtilities.class.getResourceAsStream(resource));
    }

    public static String resourceAsString(String resource) throws IOException {
        return writeValueAsString(resourceAsJson(resource));
    }

    public static <T> T resourceAsObject(String resource, Class<T> type) throws IOException {
        return mapper.readValue(TestUtilities.class.getResourceAsStream(resource), type);
    }

    public static <T> T resourceAsObject(String resource, TypeReference<T> type) throws IOException {
        return mapper.readValue(TestUtilities.class.getResourceAsStream(resource), type);
    }

    public static String resourceArrayObjectAsString(String resource, String field, String value) throws IOException {
        for (JsonNode child : resourceAsJson(resource)) {
            if(value.equals(child.get(field).asText())) {
                return writeValueAsString(child);
            }
        }
        throw new IllegalStateException("Not found.");
    }

    public static <T> T resourceArrayObject(String resource, String field, String value, Class<T> type) throws IOException {
        for (JsonNode child : resourceAsJson(resource)) {
            if(value.equals(child.get(field).asText())) {
                return mapper.readValue(writeValueAsString(child), type);
            }
        }
        throw new IllegalStateException("Not found.");
    }

    public static String writeValueAsString(Object value) throws IOException {
        return mapper.writeValueAsString(value);
    }

}
