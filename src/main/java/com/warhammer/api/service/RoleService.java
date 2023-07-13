package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warhammer.api.model.Role;
import com.warhammer.api.repository.RoleRepository;

import lombok.Data;

@Data
@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Optional<Role> getRole(final Long id){
		return roleRepository.findById(id);
	}
	public Iterable<Role> getRole() {
		return roleRepository.findAll();
	}
	public void deleteRole(final Long id) {
		roleRepository.deleteById(id);
	}
	public Role saveRole(Role role) {
		Role savedRole = roleRepository.save(role);
		return savedRole;
	}
}
