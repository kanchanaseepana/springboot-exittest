package com.exitTest.backendAngular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exitTest.backendAngular.entity.RoleDao;
import com.exitTest.backendAngular.model.Role;
import com.exitTest.backendAngular.model.User;
import com.exitTest.backendAngular.repository.RegistrationRepository;



@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository repo;
	
	@Autowired
	private RoleDao roleDao;
	
	public User saveUser(User user) {
		
		return repo.save(user);
	}
	
	public User fetchUserByEmailId(String email) {
		
		return repo.findByEmailID(email);
	}
	
public User fetchUserByEmailIdAndPassword(String email,String password) {
		
		return repo.findByEmailIDAndUserPassword(email, password);
	}

public Role createNewRole(Role role) {
	return roleDao.save(role);
}

	
	
}
