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


import com.warhammer.api.model.Stade;
import com.warhammer.api.service.StadeService;

@RestController
public class StadeController {
	@Autowired
	private StadeService stadeService;
	
	@GetMapping("/stades")
	public Iterable<Stade> getStade() {
		return stadeService.getStade();
	}
	
	@GetMapping("/stades/{id}")
	public Stade getStade(@PathVariable("id") final Long id){
		Optional<Stade> stade = stadeService.getStade(id);
		if(stade.isPresent()) {
			return stade.get();
		} else {
			return null;
		}
	}
	@PostMapping("/stades")
	public Stade createStade(@RequestBody Stade stade) {
		//stade.setPassword(passwordEncoder.encode(stade.getPassword()));
		return stadeService.saveStade(stade);
	}
	@PutMapping("/stades/{id}")
	public Stade updateStade(@PathVariable("id") final Long id, @RequestBody Stade stade) {
		//stade.setPassword(passwordEncoder.encode(stade.getPassword()));
		Optional<Stade> e = stadeService.getStade(id);
		if(e.isPresent()) {
			Stade currentStade = e.get();
			
			String nameStade = stade.getNameStade();
			if(nameStade != null) {
				currentStade.setNameStade(nameStade);
			}
			int lvlStade = stade.getLvlStade();
			if(lvlStade != 0) {
				currentStade.setLvlStade(lvlStade);;
			}
			stadeService.saveStade(currentStade);
			return currentStade;
		} else {
			return null;
		}
	}
	@PatchMapping("/stades/{id}")
	public Stade patchStade(@PathVariable("id") final Long id, @RequestBody Stade stade){
		//stade.setPassword(passwordEncoder.encode(stade.getPassword()));		
		Optional<Stade> e = stadeService.getStade(id);
		if(e.isPresent()) {
			Stade currentStade = e.get();
			
			String nameStade = stade.getNameStade();
			int lvlStade = stade.getLvlStade();
			if(nameStade != null && lvlStade != 0) {
				currentStade.setNameStade(nameStade);
				currentStade.setLvlStade(lvlStade);
				stadeService.saveStade(currentStade);
			return currentStade;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/stades/{id}")
	public void deleteStade(@PathVariable("id") final Long id) {
		stadeService.deleteStade(id);
	}
}
