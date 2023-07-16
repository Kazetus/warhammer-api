package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


import com.warhammer.api.model.User;
import com.warhammer.api.repository.UserRepository;
import com.warhammer.api.service.JwtService;
import com.warhammer.api.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	@Autowired
	private JwtService jwt;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/user")
	public Iterable<User> getUser() {
		return userService.getUser();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Long id){
		Optional<User> user = userService.getUser(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	@PutMapping({"/user/{id}", "/admin/{id}"})
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user, HttpServletRequest request) {
		String userIdentity = jwt.extractUsername(request.getHeader("Authorization").substring(7));
		Optional<User> userTest = repository.findByUsername(userIdentity);
		Optional<User> e = userService.getUser(id);
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			User currentUser = e.get();
			
			String username = user.getUsername();
			if(username != null) {
				currentUser.setUsername(username);
			}
			String mail = user.getMail();
			if(mail != null) {
				currentUser.setMail(mail);;
			}
			String password = user.getPassword();
			if(password != null) {
				currentUser.setPassword(password);
			}
			int idRole = user.getIdRole();
			if(idRole != 0) {
				currentUser.setIdRole(idRole);
			}
			userService.saveUser(currentUser);
			return currentUser;
		} else {
			return null;
		}
	}
	@PatchMapping("/user/{id}")
	public User patchUser(@PathVariable("id") final Long id, @RequestBody User user){
				
		Optional<User> e = userService.getUser(id);
		if(e.isPresent()) {
			User currentUser = e.get();
			
			String username = user.getUsername();
			String mail = user.getMail();
			String password = user.getPassword();
			int idRole = user.getIdRole();
			if(username != null && mail != null && password != null && idRole != 0) {
				currentUser.setUsername(username);
				currentUser.setMail(mail);
				currentUser.setPassword(password);
				userService.saveUser(currentUser);
			return currentUser;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final Long id) {
		userService.deleteUser(id);
	}
}
