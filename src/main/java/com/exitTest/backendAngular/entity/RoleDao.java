package com.exitTest.backendAngular.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exitTest.backendAngular.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role,String>{

}
