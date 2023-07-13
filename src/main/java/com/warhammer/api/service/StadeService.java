package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warhammer.api.model.Stade;
import com.warhammer.api.repository.StadeRepository;

import lombok.Data;

@Data
@Service
public class StadeService {
	
	@Autowired
	private StadeRepository stadeRepository;
	
	public Optional<Stade> getStade(final Long id){
		return stadeRepository.findById(id);
	}
	public Iterable<Stade> getStade() {
		return stadeRepository.findAll();
	}
	public void deleteStade(final Long id) {
		stadeRepository.deleteById(id);
	}
	public Stade saveStade(Stade stade) {
		Stade savedStade = stadeRepository.save(stade);
		return savedStade;
	}
}
