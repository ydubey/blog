package com.elega9t.sample.springboot.it.steps;

import org.hamcrest.core.StringStartsWith;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Then;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class HttpResponseStepdefs extends BaseStepdefs {

  private static final Context context = Context.instance;
  
  private static final Map<String, Integer> statusNames = new HashMap<String, Integer>() {{
    put("ok", 200);
    put("unauthorized", 401);
  }};

  @Then("^the http status code is (\\d+)$")
  public void assertResponseStatusCode(int statusCode) {
    context.getResponse().then().assertThat().statusCode(statusCode);
  }

  @Then("^I receive \"(\\w+)\" status$")
  public void assertResponseStatusCodeBasedOnName(String statusName) {
    assertResponseStatusCode(statusNames.get(statusName.toLowerCase()));
  }

  @Then("^the http header \"([^\"]+)\" is \"([^\"]+)\"$")
  public void assertResponseHeader(String header, String expectedValue) {
    context.getResponse().then().assertThat().header(header, expectedValue);
  }

  @Then("^the http header \"([^\"]+)\" is like \"([^\"]+)\"$")
  public void assertResponseHeaderLike(String header, String expectedValue) {
    context.getResponse().then().assertThat().header(header, new StringStartsWith(expectedValue));
  }

  @Then("^value in response json has (\\d+) items$")
  public void assertResponseJsonArraySize(int count) {
    assertResponseJsonHasArrayOfSize(count, "$");
  }

  @Then("^value in response json has (\\d+) items at \"([^\"]+)\"$")
  public void assertResponseJsonHasArrayOfSize(int count, String path) {
    assertThat(context.getResponse().jsonPath().getList(path).size(), is(count));
  }

  @Then("^value in response json at \"([^\"]+)\" is \"([^\"]+)\"$")
  public void assertResponseJsonValue(String jsonPath, String value) {
    context.getResponse().jsonPath().get(jsonPath).equals(value);
  }

}
