package com.exitTest.backendAngular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exitTest.backendAngular.model.User;

@Repository
public interface CountRepository extends CrudRepository<User,Long> {

}
