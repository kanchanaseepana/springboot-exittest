package com.exitTest.backendAngular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exitTest.backendAngular.model.User;

public interface RegistrationRepository extends JpaRepository<User,Integer> {

	public User findByEmailID(String emailId);
	public User findByEmailIDAndUserPassword(String emailId,String password);
	

}
