package com.elega9t.sample.springboot.it.steps;

import cucumber.api.java.Before;

public class ScenarioHooks {

  @Before
  public void beforeScenario() {
    Context.instance.reset();
  }

}
