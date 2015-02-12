package com.elega9t.sample.springboot.model.converter;

import com.elega9t.sample.springboot.model.entity.AuthorityEntity;
import com.elega9t.sample.springboot.model.view.AuthorityView;

public class AuthorityConverter {

  public static AuthorityView from(AuthorityEntity entity) {
    AuthorityView view = new AuthorityView();
    view.setId(entity.getId());
    view.setName(entity.getName());
    return view;
  }

}
