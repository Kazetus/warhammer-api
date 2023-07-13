package com.warhammer.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Faction;

public interface FactionRepository extends CrudRepository<Faction, Long>{

}
