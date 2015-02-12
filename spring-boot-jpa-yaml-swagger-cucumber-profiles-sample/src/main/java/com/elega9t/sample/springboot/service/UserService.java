package com.elega9t.sample.springboot.service;

import com.elega9t.sample.springboot.model.view.UserView;

import java.util.List;

public interface UserService {

  public List<UserView> getAll();

  public UserView getById(Long id);

}
