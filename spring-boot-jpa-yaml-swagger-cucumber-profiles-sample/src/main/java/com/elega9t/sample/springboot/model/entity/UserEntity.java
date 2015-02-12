package com.elega9t.sample.springboot.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(name = "date_of_birth", nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  private LocalDate dateOfBirth;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_authority",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id"))
  private List<AuthorityEntity> authorities;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<AuthorityEntity> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<AuthorityEntity> authorities) {
    this.authorities = authorities;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, true);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, true);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
