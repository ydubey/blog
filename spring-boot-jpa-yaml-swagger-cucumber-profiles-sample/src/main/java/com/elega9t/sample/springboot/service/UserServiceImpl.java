package com.elega9t.sample.springboot.service;

import com.elega9t.sample.springboot.ErrorCode;
import com.elega9t.sample.springboot.SampleApplicationException;
import com.elega9t.sample.springboot.model.converter.UserConverter;
import com.elega9t.sample.springboot.model.entity.UserEntity;
import com.elega9t.sample.springboot.model.view.UserView;
import com.elega9t.sample.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserView> getAll() {
    return userRepository.findAll()
        .stream().map(UserConverter::from).collect(Collectors.toList());
  }

  @Override
  public UserView getById(Long id) {
    return UserConverter.from(getEntity(id));
  }

  private UserEntity getEntity(Long id) {
    UserEntity entity = userRepository.findOne(id);
    if(entity == null)
      throw new SampleApplicationException(ErrorCode.USER_NOT_FOUND);
    return entity;
  }

}
