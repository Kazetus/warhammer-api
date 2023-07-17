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

import com.warhammer.api.model.Edition;
import com.warhammer.api.service.EditionService;

@RestController
public class EditionController {

	@Autowired
	private EditionService editionService;
	
	@GetMapping({"public/editions", "user/editions", "admin/editions"})
	public Iterable<Edition> getEdition() {
		return editionService.getEdition();
	}
	
	@GetMapping({"public/editions/{id}", "user/editions/{id}", "admin/editions/{id}"})
	public Edition getEdition(@PathVariable("id") final Long id) throws Exception{
		Optional<Edition> edition = editionService.getEdition(id);
		if(edition.isPresent()) {
			return edition.get();
		} else throw new Exception();
	}
	@PostMapping("/admin/editions")
	public Edition createEdition(@RequestBody Edition edition) {
		return editionService.saveEdition(edition);
	}
	@PutMapping("/admin/editions/{id}")
	public Edition updateEdition(@PathVariable("id") final Long id, @RequestBody Edition edition) throws Exception {
		Optional<Edition> e = editionService.getEdition(id);
		if(e.isPresent()) {
			Edition currentEdition = e.get();
			
			String editionName = edition.getEditionName();
			if(editionName != null) {
				currentEdition.setEditionName(editionName);
			}
			editionService.saveEdition(currentEdition);
			return currentEdition;
		} else throw new Exception();
	}
	@PatchMapping("/admin/editions/{id}")
	public Edition patchEdition(@PathVariable("id") final Long id, @RequestBody Edition edition) throws Exception{
		Optional<Edition> e = editionService.getEdition(id);
		if(e.isPresent()) {
			Edition currentEdition = e.get();
			
			String editionName = edition.getEditionName();
			if(editionName != null) {
				currentEdition.setEditionName(editionName);
				editionService.saveEdition(currentEdition);
			return currentEdition;
			} else throw new Exception();
		} else throw new Exception();
	}
	@DeleteMapping("/admin/editions/{id}")
	public void deleteEdition(@PathVariable("id") final Long id) {
		editionService.deleteEdition(id);
	}
}
