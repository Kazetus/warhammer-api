package com.warhammer.api.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warhammer.api.model.User;
import com.warhammer.api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ArmyService armyService;
	public Optional<User> getUser(final Long id){
		return userRepository.findById(id);
	}
	public Iterable<User> getUser() {
		return userRepository.findAll();
	}
	public Optional<User> getUserByUsername(final String username){
		return userRepository.findByUsername(username);
	}
	public Optional<User> getUserWithDataByUsername(final String username){
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			user.get().setArmy(armyService.getUserArmy(user.get().getIdUser()));
		}
		return user;
	}
	public void deleteUser(final Long id) {
		userRepository.deleteById(id);
	}
	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
}
