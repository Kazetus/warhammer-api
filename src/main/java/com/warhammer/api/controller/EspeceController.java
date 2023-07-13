package com.warhammer.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.Espece;
import com.warhammer.api.service.EspeceService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/espece")
@AllArgsConstructor

public class EspeceController {
	private EspeceService especeService;
	
	@PostMapping("/")
	public Espece create(@RequestBody Espece espece) {
		return especeService.creer(espece);
	}
	
	@GetMapping("/")
	public List<Espece> read(){
		return especeService.lire();
	}
	
	@PutMapping("/{id_espece}")
	public Espece update(@PathVariable Long id_espece, @RequestBody Espece espece){
		return especeService.modifier(id_espece, espece);	
	}
	
	@DeleteMapping("/{id_espece}")
	public String delete(@PathVariable Long id_espece){
		return especeService.supprimer(id_espece);
	}
}