package com.elega9t.sample.springboot.model.converter;

import com.elega9t.sample.springboot.model.entity.UserEntity;
import com.elega9t.sample.springboot.model.view.UserView;

import java.util.stream.Collectors;

public class UserConverter {

  public static UserView from(UserEntity entity) {
    UserView view = new UserView();
    view.setId(entity.getId());
    view.setName(entity.getName());
    view.setDateOfBirth(entity.getDateOfBirth());
    view.setAuthorities(entity.getAuthorities().stream().map(AuthorityConverter::from).collect(
        Collectors.toList()));
    return view;
  }

}
