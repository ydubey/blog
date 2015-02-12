package com.elega9t.sample.springboot.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorityView {

  @JsonProperty(required = true)
  private Long id;

  @JsonProperty(required = true)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
