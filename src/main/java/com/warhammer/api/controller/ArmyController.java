package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.warhammer.api.model.Army;
import com.warhammer.api.service.ArmyService;

@RestController
public class ArmyController {
	@Autowired
	private ArmyService armyService;
	
	@GetMapping("/army")
	public Iterable<Army> getArmy() {
		return armyService.getArmy();
	}
	
	@GetMapping("/army/{id}")
	public Army getArmy(@PathVariable("id") final Long id){
		Optional<Army> army = armyService.getArmy(id);
		if(army.isPresent()) {
			return army.get();
		} else {
			return null;
		}
	}
	@PostMapping("/army")
	public Army createArmy(@RequestBody Army army) {
		//army.setPassword(passwordEncoder.encode(army.getPassword()));
		return armyService.saveArmy(army);
	}
	@PutMapping("/army/{id}")
	public Army updateArmy(@PathVariable("id") final Long id, @RequestBody Army army) {
		//army.setPassword(passwordEncoder.encode(army.getPassword()));
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent()) {
			Army currentArmy = e.get();
			
			String armyName = army.getArmyName();
			if(armyName != null) {
				currentArmy.setArmyName(armyName);
			}
			int idUser = army.getIdUser();
			if(idUser != 0) {
				currentArmy.setIdUser(idUser);;
			}
			armyService.saveArmy(currentArmy);
			return currentArmy;
		} else {
			return null;
		}
	}
	@PatchMapping("/army/{id}")
	public Army patchArmy(@PathVariable("id") final Long id, @RequestBody Army army){
		//army.setPassword(passwordEncoder.encode(army.getPassword()));		
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent()) {
			Army currentArmy = e.get();
			
			String armyName = army.getArmyName();
			int idUser = army.getIdUser();
			if(armyName != null && idUser != 0) {
				currentArmy.setArmyName(armyName);
				currentArmy.setIdUser(idUser);
				armyService.saveArmy(currentArmy);
			return currentArmy;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/army/{id}")
	public void deleteArmy(@PathVariable("id") final Long id) {
		armyService.deleteArmy(id);
	}
}
