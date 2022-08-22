package com.rahul.demoangular.dao;

import org.springframework.data.repository.CrudRepository;

import com.rahul.demoangular.entity.User;

public interface UserRepository extends CrudRepository <User, String> {
    
}
