package com.exitTest.backendAngular.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exitTest.backendAngular.entity.RoleDao;
import com.exitTest.backendAngular.model.Role;
import com.exitTest.backendAngular.model.User;
import com.exitTest.backendAngular.service.RegistrationService;

@RestController 
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId=user.getEmailID();
		if(tempEmailId!=null && !"".equals(tempEmailId)) {
			User userobj=service.fetchUserByEmailId(tempEmailId);
			if(userobj!=null) {
				throw new Exception("User with "+tempEmailId+ "already exist");
			}
			
		}
		
		User userObj=null;
		userObj=service.saveUser(user);
		return userObj;
		
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user,HttpServletRequest request) throws Exception {
		
		String tempEmailId=user.getEmailID();
		String tempPass=user.getUserPassword();
		User userobj= null ;
		if(tempEmailId!=null && tempPass!=null) {
			
			userobj=service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);	
		}
		if(userobj == null) {
			throw new Exception("Bad Credentials");
		}
		//HttpSession session =request.getSession();
		//session.setAttribute("username",tempEmailId );
		return userobj;
		
	}
	
	
	@PostMapping({"/createNewRole"})
	public Role createNewRole(@RequestBody Role role) {
		return service.createNewRole(role);
		
	}
	

}
