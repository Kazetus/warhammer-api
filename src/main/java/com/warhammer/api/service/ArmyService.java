package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warhammer.api.model.Alliance;
import com.warhammer.api.model.Army;
import com.warhammer.api.model.Edition;
import com.warhammer.api.model.Faction;
import com.warhammer.api.repository.ArmyRepository;

import lombok.Data;

@Data
@Service
public class ArmyService {
	
	@Autowired
	private ArmyRepository armyRepository;
	@Autowired 
	private UnitsService unitsService;
	@Autowired 
	private FactionService factionService;
	@Autowired
	private AllianceService allianceService;
	@Autowired
	private EditionService editionService;
	
	public Optional<Army> getArmy(final Long id){
		Optional<Army> army = armyRepository.findById(id);
		if(army.isPresent()) {
			army.get().setUnits(unitsService.getArmyUnits(army.get().getIdArmy()));
			Optional<Faction> faction = factionService.getFaction(army.get().getIdFaction());
			if(faction.isPresent()){
				army.get().setFaction(faction.get().getFactionName());
				Optional<Alliance> alliance = allianceService.getAlliance(Long.valueOf(faction.get().getIdAlliance()));
				if(alliance.isPresent()) {
					army.get().setAlliance(alliance.get().getAllianceName());
				}
				Optional<Edition> edition = editionService.getEdition(Long.valueOf(faction.get().getIdEdition()));
				if(edition.isPresent()) {
					army.get().setEdition(edition.get().getEditionName());
				}
			}
		}
		return army;
	}
	public Iterable<Army> getUserArmy(final Long id){
		Iterable<Army> armies = armyRepository.findByIdUser(id);
		for(Army army : armies) {
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
		return armies;
	}
	public Iterable<Army> getArmy() {
		Iterable<Army> armyList = armyRepository.findAll();
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
	public void deleteArmy(final Long id) {
		armyRepository.deleteById(id);
	}
	public Army saveArmy(Army army) {
		Army savedArmy = armyRepository.save(army);
		return savedArmy;
	}
}
