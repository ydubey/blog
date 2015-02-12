package com.elega9t.sample.springboot.it.steps;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ResponseAssertions {

  private static final Context context = Context.instance;

  public static void validResponse(String schemaPath) {
    Response response = context.getResponse();
    assertThat(response.getStatusCode(), is(200));
    String userJson = response.getBody().prettyPrint();
    assertThat(userJson, matchesJsonSchemaInClasspath(schemaPath));
  }
  
}
