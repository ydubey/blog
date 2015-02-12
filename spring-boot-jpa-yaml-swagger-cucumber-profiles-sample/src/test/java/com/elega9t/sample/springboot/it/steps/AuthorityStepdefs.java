package com.elega9t.sample.springboot.it.steps;

import com.elega9t.sample.springboot.model.view.UserView;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AuthorityStepdefs extends BaseStepdefs {

  private static final Context context = Context.instance;

  @When("^I request for all authorities$")
  public void requestAllUsers() {
    context.get("api/authority");
  }

  @When("^I request for a specific authority$")
  public void requestSpecificUser() {
    context.get("api/authority/" + context.getAttributeAsString("authorityId"));
  }

  @Then("^I receive a valid list of authorities$")
  public void receiveValidListOfUser() {
    ResponseAssertions.validResponse("schema/authorities.json");
  }

  @Then("^I receive a valid authority$")
  public void receiveValidUser() {
    ResponseAssertions.validResponse("schema/authority.json");
  }

  @Then("^I use a specific authority from the list$")
  public void useSpecificUser() {
    Response response = context.getResponse();
    UserView[] cars = response.as(UserView[].class);
    context.setAttribute("authorityId", cars[0].getId());
  }

}
