package com.elega9t.sample.springboot.service;

import com.elega9t.sample.springboot.ErrorCode;
import com.elega9t.sample.springboot.SampleApplicationException;
import com.elega9t.sample.springboot.model.converter.AuthorityConverter;
import com.elega9t.sample.springboot.model.entity.AuthorityEntity;
import com.elega9t.sample.springboot.model.view.AuthorityView;
import com.elega9t.sample.springboot.repository.AuthorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  private AuthorityRepository authorityRepository;

  @Autowired
  public AuthorityServiceImpl(
      AuthorityRepository authorityRepository) {
    this.authorityRepository = authorityRepository;
  }

  @Override
  public List<AuthorityView> getAll() {
    return authorityRepository.findAll()
        .stream().map(AuthorityConverter::from).collect(Collectors.toList());
  }

  @Override
  public AuthorityView getById(Long id) {
    return AuthorityConverter.from(getEntity(id));
  }

  @Override
  public void deleteById(Long id) {
    authorityRepository.delete(id);
  }

  private AuthorityEntity getEntity(Long id) {
    AuthorityEntity entity = authorityRepository.findOne(id);
    if(entity == null)
      throw new SampleApplicationException(ErrorCode.AUTHORITY_NOT_FOUND);
    return entity;
  }

}
