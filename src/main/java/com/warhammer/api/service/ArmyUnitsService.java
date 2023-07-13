package com.warhammer.api.service;

import com.warhammer.api.model.ArmyUnits;
import com.warhammer.api.repository.ArmyUnitsRepository;

import lombok.Data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ArmyUnitsService {
	@Autowired
	private ArmyUnitsRepository armyUnitsRepository;
	
	public Optional<ArmyUnits> getArmyUnits(final Long id){
		return armyUnitsRepository.findById(id);
	}
	public Iterable<ArmyUnits> getArmyUnits() {
		return armyUnitsRepository.findAll();
	}
	public void deleteArmyUnits(final Long id) {
		armyUnitsRepository.deleteById(id);
	}
	public ArmyUnits saveArmyUnits(ArmyUnits armyUnits) {
		ArmyUnits savedArmyUnits = armyUnitsRepository.save(armyUnits);
		return savedArmyUnits;
	}
}