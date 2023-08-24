package com.warhammer.api.service;

import com.warhammer.api.model.Units;
import com.warhammer.api.repository.UnitsRepository;

import lombok.Data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UnitsService {
	@Autowired
	private UnitsRepository unitsRepository;
	
	public Optional<Units> getUnits(final Long id){
		return unitsRepository.findById(id);
	}
	public Iterable<Units> getUnits() {
		return unitsRepository.findAll();
	}
	public Iterable<Units> getArmyUnits(final Long id){
		return unitsRepository.findUnitsByArmy(id);
	}
	public Iterable<Units> getFactionUnits(final Long id) {
		return unitsRepository.findUnitsByFaction(id);
	}
	public void deleteUnits(final Long id) {
		unitsRepository.deleteById(id);
	}
	public Units saveUnits(Units units) {
		Units savedUnits = unitsRepository.save(units);
		return savedUnits;
	}
}