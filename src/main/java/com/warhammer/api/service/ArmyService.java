package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warhammer.api.model.Army;
import com.warhammer.api.repository.ArmyRepository;

import lombok.Data;

@Data
@Service
public class ArmyService {
	
	@Autowired
	private ArmyRepository armyRepository;
	
	public Optional<Army> getArmy(final Long id){
		return armyRepository.findById(id);
	}
	public Iterable<Army> getArmy() {
		return armyRepository.findAll();
	}
	public void deleteArmy(final Long id) {
		armyRepository.deleteById(id);
	}
	public Army saveArmy(Army army) {
		Army savedArmy = armyRepository.save(army);
		return savedArmy;
	}
}
