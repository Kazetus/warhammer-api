package com.warhammer.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Army;

public interface ArmyRepository extends CrudRepository<Army, Long>{
	@Query(value="SELECT id_army, army_name,id_user FROM army WHERE id_user = ?", nativeQuery = true)
	public Iterable<Army> findByIdUser(final Long id);
}
