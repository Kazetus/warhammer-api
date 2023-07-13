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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.Units;
import com.warhammer.api.service.UnitsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UnitsController {
	@Autowired
	private UnitsService unitsService;
	
	@GetMapping({"public/units", "user/units", "admin/units"})
	public Iterable<Units> getUnits() {
		return unitsService.getUnits();
	}
	
	@GetMapping({"public/units/{id}", "user/units/{id}", "admin/units/{id}"})
	public Units getUnits(@PathVariable("id") final Long id){
		Optional<Units> units = unitsService.getUnits(id);
		if(units.isPresent()) {
			return units.get();
		} else {
			return null;
		}
	}
	@PostMapping("admin/units")
	public Units createUnits(@RequestBody Units units) {
		
		return unitsService.saveUnits(units);
	}
	@PutMapping("admin/units/{id}")
	public Units updateUnits(@PathVariable("id") final Long id, @RequestBody Units units) {
		
		Optional<Units> e = unitsService.getUnits(id);
		if(e.isPresent()) {
			Units currentUnits = e.get();
			
			String nameUnits = units.getUnitsName();
			int nombreFigurine = units.getNombreFigurine();
			int points = units.getPoints();
			int idFaction = units.getIdFaction();
			if(nameUnits != null) {
				currentUnits.setUnitsName(nameUnits);
			}
			if(nombreFigurine != 0) {
				currentUnits.setNombreFigurine(nombreFigurine);
			}

			if(points != 0) {
				currentUnits.setPoints(points);
			}

			if(idFaction != 0) {
				currentUnits.setIdFaction(idFaction);
			}
			unitsService.saveUnits(currentUnits);
			return currentUnits;
		} else {
			return null;
		}
	}
	@PatchMapping("admin/units/{id}")
	public Units patchUnits(@PathVariable("id") final Long id, @RequestBody Units units){
				
		Optional<Units> e = unitsService.getUnits(id);
		if(e.isPresent()) {
			Units currentUnits = e.get();
			
			String nameUnits = units.getUnitsName();

			int nombreFigurine = units.getNombreFigurine();
			int points = units.getPoints();
			int idFaction = units.getIdFaction();
			if(nameUnits != null && nombreFigurine != 0 && points != 0 && idFaction != 0) {
				currentUnits.setUnitsName(nameUnits);
				currentUnits.setNombreFigurine(nombreFigurine);
				currentUnits.setPoints(points);
				currentUnits.setIdFaction(idFaction);
				unitsService.saveUnits(currentUnits);
			return currentUnits;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("admin/units/{id}")
	public void deleteUnits(@PathVariable("id") final Long id) {
		unitsService.deleteUnits(id);
	}
}