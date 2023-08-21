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

import com.warhammer.api.model.Alliance;
import com.warhammer.api.model.Army;
import com.warhammer.api.model.Edition;
import com.warhammer.api.model.Faction;
import com.warhammer.api.model.User;
import com.warhammer.api.repository.UserRepository;
import com.warhammer.api.service.AllianceService;
import com.warhammer.api.service.ArmyService;
import com.warhammer.api.service.EditionService;
import com.warhammer.api.service.FactionService;
import com.warhammer.api.service.JwtService;
import com.warhammer.api.service.UnitsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@RestController
@CrossOrigin
@AllArgsConstructor
public class ArmyController {
	@Autowired
	private ArmyService armyService;
	@Autowired
	private UnitsService unitsService;
	@Autowired
	private FactionService factionService;
	@Autowired
	private AllianceService allianceService;
	@Autowired
	private EditionService editionService;
	@Autowired 
	private UserRepository repository;
	@Autowired
	private JwtService jwt;
	
	@GetMapping({"/public/army", "/user/army", "/admin/army"})
	public Iterable<Army> getArmy() {
		Iterable<Army> armyList = armyService.getArmy();
		for(Army army : armyList) {
			army.setUnits(unitsService.getArmyUnits(army.getIdArmy()));
			Optional<Faction> faction = factionService.getFaction(army.getIdFaction());
			if(faction.isPresent()){
				army.setFaction(faction.get().getFactionName());
				Optional<Alliance> alliance = allianceService.getAlliance(Long.valueOf(faction.get().getIdAlliance()));
				if(alliance.isPresent()) {
					army.setAlliance(alliance.get().getAllianceName());
				}
				Optional<Edition> edition = editionService.getEdition(Long.valueOf(faction.get().getIdEdition()));
				if(edition.isPresent()) {
					army.setEdition(edition.get().getEditionName());
				}
			}
		}
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
	public Army createArmy(@RequestBody Army army) {
		return armyService.saveArmy(army);
	}
	@PutMapping({"/user/army/{id}", "/admin/army/{id}"})
	public Army updateArmy(@PathVariable("id") final Long id, @RequestBody Army army, HttpServletRequest request) throws Exception {
		// Verify user before modify
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
		// Verify user before delete
		Optional<User> userTest = repository.findByUsername(jwt.extractUsername(request.getHeader("Authorization").substring(7)));
		Optional<Army> e = armyService.getArmy(id);
		if(e.isPresent() && e.get().getIdUser() == userTest.get().getIdUser() || userTest.get().getIdRole() == 1) {
		armyService.deleteArmy(id);
		} else throw new Exception();
	}
}
