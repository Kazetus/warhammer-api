package com.warhammer.api.repository;

import com.warhammer.api.model.ArmyUnits;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArmyUnitsRepository extends CrudRepository<ArmyUnits, Long> {
	@Query(value ="SELECT id_army_units, id_army, units.id_units, units_name, nombre_figurine, points, id_faction FROM units INNER JOIN army_units ON army_units.id_units = units.id_units WHERE army_units.id_army = ?", nativeQuery = true)
	Iterable<ArmyUnits> findArmyUnitsByArmy (Long id_army);
}