package com.exitTest.backendAngular.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	
	
	@Id
	private String userName;
	private String emailID;
	private String userFirstName;
	private String userPassword;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLE",
	joinColumns= {
			@JoinColumn(name="USER_ID")
	},
	         inverseJoinColumns= {
	         @JoinColumn(name="ROLE_ID")
	         }
	)
	private Set<Role> role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
}
	
	