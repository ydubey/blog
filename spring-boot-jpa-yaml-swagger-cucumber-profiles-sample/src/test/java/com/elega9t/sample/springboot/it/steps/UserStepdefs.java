package com.elega9t.sample.springboot.it.steps;

import com.elega9t.sample.springboot.model.view.UserView;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStepdefs extends BaseStepdefs {

  private static final Context context = Context.instance;

  @When("^I request for all users$")
  public void requestAllUsers() {
    context.get("api/user");
  }

  @When("^I request for a specific user$")
  public void requestSpecificUser() {
    context.get("api/user/" + context.getAttributeAsString("userId"));
  }

  @Then("^I receive a valid list of users$")
  public void receiveValidListOfUser() {
    ResponseAssertions.validResponse("schema/users.json");
  }

  @Then("^I receive a valid user$")
  public void receiveValidUser() {
    ResponseAssertions.validResponse("schema/user.json");
  }

  @Then("^I use a specific user from the list$")
  public void useSpecificUser() {
    Response response = context.getResponse();
    UserView[] cars = response.as(UserView[].class);
    context.setAttribute("userId", cars[0].getId());
  }

}
