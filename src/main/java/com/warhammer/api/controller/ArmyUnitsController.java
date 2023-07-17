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
import com.warhammer.api.model.ArmyUnits;
import com.warhammer.api.model.User;
import com.warhammer.api.repository.ArmyRepository;
import com.warhammer.api.repository.UserRepository;
import com.warhammer.api.service.ArmyUnitsService;
import com.warhammer.api.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ArmyUnitsController {
	@Autowired
	private ArmyUnitsService armyUnitsService;
	@Autowired 
	private UserRepository repository;
	@Autowired
	private ArmyRepository armyRepository;
	@Autowired
	private JwtService jwt;
	
	
	@GetMapping({"/public/armyunits", "/user/armyunits", "/admin/armyunits"})
	public Iterable<ArmyUnits> getArmyUnits() {
		return armyUnitsService.getArmyUnits();
	}
	
	@GetMapping({"/public/armyunits/{id}", "/user/armyunits/{id}", "/admin/armyunits/{id}"})
	public ArmyUnits getArmyUnits(@PathVariable("id") final Long id) throws Exception{
		Optional<ArmyUnits> armyUnits = armyUnitsService.getArmyUnits(id);
		if(armyUnits.isPresent()) {
			return armyUnits.get();
		} else throw new Exception();
	}
	@PostMapping({"/user/armyunits", "/admin/armyunits"})
	public ArmyUnits createArmyUnits(@RequestBody ArmyUnits armyUnits) {
		
		return armyUnitsService.saveArmyUnits(armyUnits);
	}
	@PutMapping({"/user/armyunits/{id}", "/admin/armyunits/{id}"})
	public ArmyUnits updateArmyUnits(@PathVariable("id") final Long id, @RequestBody ArmyUnits armyUnits, HttpServletRequest request) throws Exception {
		// Verify user before modify

		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<ArmyUnits> e = armyUnitsService.getArmyUnits(id);
		Optional<Army> a = armyRepository.findById((long) e.get().getIdArmy());
		if(e.isPresent() && a.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
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
		} else throw new Exception();
	}
	@PatchMapping({"/user/armyunits/{id}", "/admin/armyunits/{id}"})
	public ArmyUnits patchArmyUnits(@PathVariable("id") final Long id, @RequestBody ArmyUnits armyUnits, HttpServletRequest request) throws Exception{		
		// Verify user before modify

		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<ArmyUnits> e = armyUnitsService.getArmyUnits(id);
		Optional<Army> a = armyRepository.findById((long) e.get().getIdArmy());
		if(e.isPresent() && a.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
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
			} else throw new Exception();
		} else throw new Exception();
	}
	@DeleteMapping({"/user/armyunits/{id}", "/admin/armyunits/{id}"})
	public void deleteArmyUnits(@PathVariable("id") final Long id, HttpServletRequest request) throws Exception {
		// Verify user before modify

		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<ArmyUnits> e = armyUnitsService.getArmyUnits(id);
		Optional<Army> a = armyRepository.findById((long) e.get().getIdArmy());
		if(e.isPresent() && a.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			armyUnitsService.deleteArmyUnits(id);
		} else throw new Exception();
	}
}
