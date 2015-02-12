package com.elega9t.sample.springboot.it.steps;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class Context {

  public static Context instance = new Context();

  private RequestSpecification requestSpecification;
  private Response response;
  private Map<String, Object> attributes = new HashMap<>();

  public void reset() {
    response = null;
    requestSpecification = given().log().everything();
    attributes.clear();
  }

  public void setAttribute(String name, Object value) {
    attributes.put(name, value);
  }

  public Object getAttribute(String name) {
    return attributes.get(name);
  }

  public String getAttributeAsString(String name) {
    return String.valueOf(getAttribute(name));
  }

  public void setRequestSpecification(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
  }

  public void setBasicAuth(String username, String password) {
    requestSpecification = requestSpecification
        .auth().preemptive().basic(username, password);
  }

  public Response getResponse() {
    return response;
  }

  public void get(String path) {
    response = requestSpecification.get(path);
  }

  public void post(String path) {
    response = requestSpecification.post(path);
  }

}
