package com.elega9t.sample.springboot.service;

import com.elega9t.sample.springboot.model.view.AuthorityView;

import java.util.List;

public interface AuthorityService {

  public List<AuthorityView> getAll();

  AuthorityView getById(Long id);

  void deleteById(Long id);
  
}
