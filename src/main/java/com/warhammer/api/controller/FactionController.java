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

import com.warhammer.api.model.Faction;
import com.warhammer.api.service.FactionService;

public class FactionController {
	@Autowired
	private FactionService factionService;
	
	@GetMapping({"/public/faction", "/user/faction", "/admin/faction"})
	public Iterable<Faction> getFaction() {
		return factionService.getFaction();
	}
	
	@GetMapping({"/public/faction/{id}", "/user/faction/{id}", "/admin/faction/{id}"})
	public Faction getFaction(@PathVariable("id") final Long id) throws Exception{
		Optional<Faction> faction = factionService.getFaction(id);
		if(faction.isPresent()) {
			return faction.get();
		} else throw new Exception();
	}
	@PostMapping("/admin/faction")
	public Faction createFaction(@RequestBody Faction faction) {
		//faction.setPassword(passwordEncoder.encode(faction.getPassword()));
		return factionService.saveFaction(faction);
	}
	@PutMapping("/admin/faction/{id}")
	public Faction upidAlliance(@PathVariable("id") final Long id, @RequestBody Faction faction) throws Exception {
		//faction.setPassword(passwordEncoder.encode(faction.getPassword()));
		Optional<Faction> e = factionService.getFaction(id);
		if(e.isPresent()) {
			Faction currentFaction = e.get();
			
			String factionName = faction.getFactionName();
			if(factionName != null) {
				currentFaction.setFactionName(factionName);
			}
			int idAlliance = faction.getIdAlliance();
			if(idAlliance != 0) {
				currentFaction.setIdAlliance(idAlliance);
			}
			int idEdition = faction.getIdEdition();
			if(idEdition != 0) {
				currentFaction.setIdEdition(idEdition);;
			}
			factionService.saveFaction(currentFaction);
			return currentFaction;
		} else throw new Exception();
	}
	@PatchMapping("/admin/faction/{id}")
	public Faction patchFaction(@PathVariable("id") final Long id, @RequestBody Faction faction) throws Exception{
		//faction.setPassword(passwordEncoder.encode(faction.getPassword()));		
		Optional<Faction> e = factionService.getFaction(id);
		if(e.isPresent()) {
			Faction currentFaction = e.get();
			
			String factionName = faction.getFactionName();
			int idAlliance = faction.getIdAlliance();
			int idEdition = faction.getIdEdition();
			if(factionName != null && idAlliance != 0 && idEdition != 0) {
				currentFaction.setFactionName(factionName);
				currentFaction.setIdAlliance(idAlliance);
				currentFaction.setIdEdition(idEdition);
				factionService.saveFaction(currentFaction);
			return currentFaction;
			} else throw new Exception();
		} else throw new Exception();
	}
	@DeleteMapping("/admin/faction/{id}")
	public void deleteFaction(@PathVariable("id") final Long id) {
		factionService.deleteFaction(id);
	}
}
