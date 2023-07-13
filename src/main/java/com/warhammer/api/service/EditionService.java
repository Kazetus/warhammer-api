package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warhammer.api.model.Edition;
import com.warhammer.api.repository.EditionRepository;

import lombok.Data;

@Data
@Service
public class EditionService {
	@Autowired
	private EditionRepository editionRepository;
	
	public Optional<Edition> getEdition(final Long id){
		return editionRepository.findById(id);
	}
	public Iterable<Edition> getEdition() {
		return editionRepository.findAll();
	}
	public void deleteEdition(final Long id) {
		editionRepository.deleteById(id);
	}
	public Edition saveEdition(Edition edition) {
		Edition savedEdition = editionRepository.save(edition);
		return savedEdition;
	}
}
