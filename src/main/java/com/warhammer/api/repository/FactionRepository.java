package com.warhammer.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Faction;

public interface FactionRepository extends CrudRepository<Faction, Long>{
	@Query(value = "Select id_faction, faction_name, id_alliance, id_edition FROM faction WHERE id_alliance = ?", nativeQuery = true)
	public Iterable<Faction> findFactionByAlliance(final Long id); 
}
