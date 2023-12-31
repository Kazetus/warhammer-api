package com.warhammer.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.Alliance;
import com.warhammer.api.service.AllianceService;
import com.warhammer.api.service.FactionService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AllianceController {
	@Autowired
	private AllianceService allianceService;
	@Autowired
	private FactionService factionService;
	
	@GetMapping({"/public/alliance","user/alliance","admin/alliance"})
	public Iterable<Alliance> getAlliance() {
		Iterable<Alliance> alliance = allianceService.getAlliance();
		for(Alliance ally : alliance) {
			ally.setFaction(factionService.getFactionAlliance(Long.valueOf(ally.getIdAlliance())));
		}
		return alliance;
	}
	
	@GetMapping({"/public/alliance/{id}","user/alliance/id","admin/alliance/{id}"})
	public Alliance getAlliance(@PathVariable("id") final Long id) throws Exception{
		Optional<Alliance> alliance = allianceService.getAlliance(id);
		if(alliance.isPresent()) {
			return alliance.get();
		} else throw new Exception();
	}
	@PostMapping("/admin/alliance")
	public Alliance createAlliance(@RequestBody Alliance alliance) {
		return allianceService.saveAlliance(alliance);
	}
	@PutMapping("/admin/alliance/{id}")
	public Alliance updateAlliance(@PathVariable("id") final Long id, @RequestBody Alliance alliance) throws Exception {
		Optional<Alliance> e = allianceService.getAlliance(id);
		if(e.isPresent()) {
			Alliance currentAlliance = e.get();
			
			String nameAlliance = alliance.getAllianceName();
			if(nameAlliance != null) {
				currentAlliance.setAllianceName(nameAlliance);
			}
			allianceService.saveAlliance(currentAlliance);
			return currentAlliance;
		} else throw new Exception();
	}
	@PatchMapping("/admin/alliance/{id}")
	public Alliance patchAlliance(@PathVariable("id") final Long id, @RequestBody Alliance alliance) throws Exception{
		Optional<Alliance> e = allianceService.getAlliance(id);
		if(e.isPresent()) {
			Alliance currentAlliance = e.get();
			
			String nameAlliance = alliance.getAllianceName();
			if(nameAlliance != null) {
				currentAlliance.setAllianceName(nameAlliance);
				allianceService.saveAlliance(currentAlliance);
			return currentAlliance;
			} else throw new Exception();
		} else throw new Exception();
	}
	@DeleteMapping("/admin/alliance/{id}")
	public void deleteAlliance(@PathVariable("id") final Long id) {
		allianceService.deleteAlliance(id);
	}
}