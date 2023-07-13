package com.warhammer.api.service;

import com.warhammer.api.model.Alliance;
import com.warhammer.api.repository.AllianceRepository;

import lombok.Data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AllianceService {
	@Autowired
	private AllianceRepository allianceRepository;
	
	public Optional<Alliance> getAlliance(final Long id){
		return allianceRepository.findById(id);
	}
	public Iterable<Alliance> getAlliance() {
		return allianceRepository.findAll();
	}
	public void deleteAlliance(final Long id) {
		allianceRepository.deleteById(id);
	}
	public Alliance saveAlliance(Alliance alliance) {
		Alliance savedAlliance = allianceRepository.save(alliance);
		return savedAlliance;
	}
}