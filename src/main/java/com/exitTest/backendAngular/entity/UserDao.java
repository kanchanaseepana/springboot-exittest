package com.exitTest.backendAngular.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exitTest.backendAngular.model.User;

@Repository
public interface UserDao extends CrudRepository<User,String> {
	
	

}
