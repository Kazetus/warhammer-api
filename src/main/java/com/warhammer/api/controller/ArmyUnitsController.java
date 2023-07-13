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


import com.warhammer.api.model.ArmyUnits;
import com.warhammer.api.service.ArmyUnitsService;

@RestController
public class ArmyUnitsController {
	@Autowired
	private ArmyUnitsService armyUnitsService;
	
	@GetMapping("/armyUnits")
	public Iterable<ArmyUnits> getArmyUnits() {
		return armyUnitsService.getArmyUnits();
	}
	
	@GetMapping("/armyUnits/{id}")
	public ArmyUnits getArmyUnits(@PathVariable("id") final Long id){
		Optional<ArmyUnits> armyUnits = armyUnitsService.getArmyUnits(id);
		if(armyUnits.isPresent()) {
			return armyUnits.get();
		} else {
			return null;
		}
	}
	@PostMapping("/armyUnits")
	public ArmyUnits createArmyUnits(@RequestBody ArmyUnits armyUnits) {
		
		return armyUnitsService.saveArmyUnits(armyUnits);
	}
	@PutMapping("/armyUnits/{id}")
	public ArmyUnits updateArmyUnits(@PathVariable("id") final Long id, @RequestBody ArmyUnits armyUnits) {
		
		Optional<ArmyUnits> e = armyUnitsService.getArmyUnits(id);
		if(e.isPresent()) {
			ArmyUnits currentArmyUnits = e.get();
			
			int quantity = armyUnits.getQuantity();
			if(quantity != 0) {
				currentArmyUnits.setQuantity(quantity);
			}
			int idArmy = armyUnits.getIdArmy();
			if(idArmy != 0) {
				currentArmyUnits.setIdArmy(idArmy);;
			}
			int idUnits = armyUnits.getIdUnits();
			if(idUnits != 0) {
				currentArmyUnits.setIdUnits(idUnits);;
			}
			armyUnitsService.saveArmyUnits(currentArmyUnits);
			return currentArmyUnits;
		} else {
			return null;
		}
	}
	@PatchMapping("/armyUnits/{id}")
	public ArmyUnits patchArmyUnits(@PathVariable("id") final Long id, @RequestBody ArmyUnits armyUnits){		
		Optional<ArmyUnits> e = armyUnitsService.getArmyUnits(id);
		if(e.isPresent()) {
			ArmyUnits currentArmyUnits = e.get();
			int quantity = armyUnits.getQuantity();
			int idArmy = armyUnits.getIdArmy();
			int idUnits = armyUnits.getIdUnits();
			if(quantity != 0 && idArmy != 0 && idUnits !=0) {
				currentArmyUnits.setQuantity(quantity);
				currentArmyUnits.setIdArmy(idArmy);
				currentArmyUnits.setIdUnits(idUnits);
				armyUnitsService.saveArmyUnits(currentArmyUnits);
			return currentArmyUnits;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/armyUnits/{id}")
	public void deleteArmyUnits(@PathVariable("id") final Long id) {
		armyUnitsService.deleteArmyUnits(id);
	}
}
