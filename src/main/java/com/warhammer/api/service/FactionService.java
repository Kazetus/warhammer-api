package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warhammer.api.model.Faction;
import com.warhammer.api.repository.FactionRepository;

import lombok.Data;

@Data
@Service
public class FactionService {

	@Autowired
	private FactionRepository factionRepository;
	
	public Optional<Faction> getFaction(final Long id){
		return factionRepository.findById(id);
	}
	public Iterable<Faction> getFaction() {
		return factionRepository.findAll();
	}
	public void deleteFaction(final Long id) {
		factionRepository.deleteById(id);
	}
	public Faction saveFaction(Faction faction) {
		Faction savedFaction = factionRepository.save(faction);
		return savedFaction;
	}

}
