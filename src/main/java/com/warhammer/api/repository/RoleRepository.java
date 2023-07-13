package com.warhammer.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
}
