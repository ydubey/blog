package com.elega9t.sample.springboot.model.view;

import com.elega9t.sample.springboot.model.view.codec.JodaLocalDateDeserializer;
import com.elega9t.sample.springboot.model.view.codec.JodaLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.joda.time.LocalDate;

import java.util.List;

public class UserView {

  @JsonProperty(required = true)
  private Long id;

  @JsonProperty(required = true)
  private String name;

  @JsonProperty(value = "date_of_birth", required = true)
  @JsonSerialize(using = JodaLocalDateSerializer.class)
  @JsonDeserialize(using = JodaLocalDateDeserializer.class)
  private LocalDate dateOfBirth;

  @JsonProperty(required = true)
  private List<AuthorityView> authorities;

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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<AuthorityView> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<AuthorityView> authorities) {
    this.authorities = authorities;
  }

}
