package com.warhammer.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.Role;
import com.warhammer.api.service.RoleService;

@RestController
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role")
	public Iterable<Role> getRole() {
		return roleService.getRole();
	}
	
	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable("id") final Long id){
		Optional<Role> role = roleService.getRole(id);
		if(role.isPresent()) {
			return role.get();
		} else {
			return null;
		}
	}
	@PostMapping("/role")
	public Role createRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}
	@PutMapping("/role/{id}")
	public Role updateRole(@PathVariable("id") final Long id, @RequestBody Role role) {
		Optional<Role> e = roleService.getRole(id);
		if(e.isPresent()) {
			Role currentRole = e.get();
			
			String roleName = role.getRoleName();
			if(roleName != null) {
				currentRole.setRoleName(roleName);
			}
			roleService.saveRole(currentRole);
			return currentRole;
		} else {
			return null;
		}
	}
	@PatchMapping("/role/{id}")
	public Role patchRole(@PathVariable("id") final Long id, @RequestBody Role role){
		Optional<Role> e = roleService.getRole(id);
		if(e.isPresent()) {
			Role currentRole = e.get();
			
			String roleName = role.getRoleName();
			if(roleName != null) {
				currentRole.setRoleName(roleName);
				roleService.saveRole(currentRole);
			return currentRole;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/role/{id}")
	public void deleteRole(@PathVariable("id") final Long id) {
		roleService.deleteRole(id);
	}
}
