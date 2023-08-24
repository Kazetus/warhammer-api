package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.User;
import com.warhammer.api.repository.UserRepository;
import com.warhammer.api.service.JwtService;
import com.warhammer.api.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	@Autowired
	private JwtService jwt;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/admin/user")
	public Iterable<User> getUser() {
		return userService.getUser();
	}
	
	@GetMapping({"/user/user/{id}", "/admin/user/{id}"})
	public User getUser(@PathVariable("id") final Long id, HttpServletRequest request) throws Exception{
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<User> user = userService.getUser(id);
		if(user.isPresent() && user.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			return user.get();
		} else throw new Exception();
	}
	@GetMapping("/public/{username}")
	public Boolean getUser(@PathVariable("username") final String username) throws Exception {
		Optional<User> user = userService.getUserByUsername(username);
		return user.isPresent();
	}
	@PutMapping({"/user/user/{id}", "/admin/user/{id}"})
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user, HttpServletRequest request) throws Exception {
		// Checking that the user trying to modify an user is either this user or an admin.
		// Getting back the user from the token send with the request
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		// getting the user that is going to be modified
		Optional<User> e = userService.getUser(id);
		// Checking that an user can only alter his own account or being an admin.
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			User currentUser = e.get();
			// Checking the data we want to modify.
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
			if(idRole != 0 && userTest.get().getIdRole() == 1) {
				currentUser.setIdRole(idRole);
			}
			userService.saveUser(currentUser);
			return currentUser;
		} else throw new Exception();
	}
	@PatchMapping("/admin/user/{id}")
	public User patchUser(@PathVariable("id") final Long id, @RequestBody User user, HttpServletRequest request) throws Exception{
		// Checking that the user trying to modify an user is either this user or an admin.
		// Getting back the user from the token send with the request
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		// getting the user that is going to be modified
		Optional<User> e = userService.getUser(id);
		// Checking that an user can only alter his own account or being an admin.
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			User currentUser = e.get();
			
			String username = user.getUsername();
			String mail = user.getMail();
			String password = user.getPassword();
			int idRole = user.getIdRole();
			if(username != null && mail != null && password != null && idRole != 0 && userTest.get().getIdRole() == 1) {
				currentUser.setUsername(username);
				currentUser.setMail(mail);
				currentUser.setPassword(password);
				currentUser.setIdRole(idRole);
				userService.saveUser(currentUser);
			return currentUser;
			} throw new Exception();
		}  throw new Exception();
	}
	@DeleteMapping({"/user/user/{id}", "/admin/user/{id}"})
	public void deleteUser(@PathVariable("id") final Long id, HttpServletRequest request) throws Exception {
		// Checking that the user trying to modify an user is either this user or an admin.
				// Getting back the user from the token send with the request
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		// getting the user that is going to be deleted
		Optional<User> user = userService.getUser(id);
		// Checking that an user can only alter his own account or being an admin.
		if(user.isPresent() && user.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			userService.deleteUser(id);
		} throw new Exception();
	}
}
