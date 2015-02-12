package com.elega9t.sample.springboot.repository;

import com.elega9t.sample.springboot.model.entity.AuthorityEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

}