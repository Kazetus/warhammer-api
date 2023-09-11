package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.warhammer.api.model.Units;
import com.warhammer.api.model.User;
import com.warhammer.api.repository.UserRepository;
import com.warhammer.api.service.ArmyService;
import com.warhammer.api.service.ArmyUnitsService;
import com.warhammer.api.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@RestController
@CrossOrigin
@AllArgsConstructor
public class ArmyController {
	@Autowired
	private ArmyService armyService;
	@Autowired
	private ArmyUnitsService armyUnitsService;
	@Autowired 
	private UserRepository repository;
	@Autowired
	private JwtService jwt;
	@GetMapping({"/public/army", "/user/army", "/admin/army"})
	public Iterable<Army> getArmy() {
		Iterable<Army> armyList = armyService.getArmy();
		return armyList; 
	}
	@GetMapping({"/public/army/{id}", "/user/army/{id}", "/admin/army/{id}"})
	public Army getArmy(@PathVariable("id") final Long id) throws Exception{
		Optional<Army> army = armyService.getArmy(id);
		if(army.isPresent()) {
			return army.get();
		} else throw new Exception();
	}
	@PostMapping({"/user/army","/admin/army"})
	public Army createArmy(@RequestBody Army army, HttpServletRequest request) {
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("authorization").substring(7)));
		army.setIdUser(Math.toIntExact(userTest.get().getIdUser()));
		army = armyService.saveArmy(army);
		for(Units unit : army.getUnits()) {
			ArmyUnits armyUnits = new ArmyUnits(Long.valueOf(1),(int) unit.getIdUnits(), (int) army.getIdArmy());
			armyUnitsService.saveArmyUnits(armyUnits);
		}
		return army;
	}
	@PutMapping({"/user/army/{id}", "/admin/army/{id}"})
	public Army updateArmy(@PathVariable("id") final Long id, @RequestBody Army army, HttpServletRequest request) throws Exception {
		// Verify user before editing
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
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
		} else throw new Exception();
	}
	@PatchMapping({"/user/army/{id}", "/admin/army/{id}"})
	public Army patchArmy(@PathVariable("id") final Long id, @RequestBody Army army, HttpServletRequest request) throws Exception{
		// Verify user before modify
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			Army currentArmy = e.get();
			
			String armyName = army.getArmyName();
			int idUser = army.getIdUser();
			if(armyName != null && idUser != 0) {
				currentArmy.setArmyName(armyName);
				currentArmy.setIdUser(idUser);
				armyService.saveArmy(currentArmy);
			return currentArmy;
			} else throw new Exception();
		} else throw new Exception();
	}
	@DeleteMapping({"/user/army/{id}", "/admin/army/{id}"})
	public void deleteArmy(@PathVariable("id") final Long id, HttpServletRequest request) throws Exception {
		System.out.println("ici");
		// Verify user before delete
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
			armyService.deleteArmy(id);
		} else throw new Exception();
	}
}
