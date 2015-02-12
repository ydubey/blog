package com.elega9t.sample.springboot.repository;

import com.elega9t.sample.springboot.model.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}