package com.exitTest.backendAngular.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exitTest.backendAngular.entity.RoleDao;
import com.exitTest.backendAngular.entity.UserDao;
import com.exitTest.backendAngular.model.Role;
import com.exitTest.backendAngular.model.User;
import com.exitTest.backendAngular.repository.CountRepository;
import com.exitTest.backendAngular.repository.RegistrationRepository;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CountRepository countRepository;
	

	public User registerNewUser(User user) {
		
		Role role=roleDao.findById("User").get();
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		
		return userDao.save(user);
		
		
	}
	
	public void initRolesAndUser() {
		
		Role adminRole=new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("All Acess");
		roleDao.save(adminRole);
		
		Role userRole=new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Limited Acess");
		roleDao.save(userRole);
		
		User adminUser=new User();
		adminUser.setUserName("Admin");
		adminUser.setEmailID("admin@gmail.com");
		adminUser.setUserPassword(getEncodedPassword("admin"));
		adminUser.setUserFirstName("admin");
		Set<Role> adminRoles=new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);
		
		//User normalUser=new User();
		//normalUser.setUserName("Ram");
		//normalUser.setEmailID("Ram@gmail.com");
		//normalUser.setUserPassword(getEncodedPassword("1234"));
		//normalUser.setUserFirstName("sush");
		//Set<Role> userRoles=new HashSet<>();
		//userRoles.add(userRole);
		//normalUser.setRole( userRoles);
		//userDao.save(normalUser);
		
		
	}
	  public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
	  public long countUser() {
			 return countRepository.count();
		}
	 

}
