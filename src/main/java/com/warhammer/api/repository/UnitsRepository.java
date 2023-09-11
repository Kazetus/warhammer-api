package com.warhammer.api.repository;

import com.warhammer.api.model.ArmyUnits;
import com.warhammer.api.model.Units;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UnitsRepository extends CrudRepository<Units, Long> {
	@Query(value ="SELECT id_army_units, units.id_units, units_name, nombre_figurine, points, id_faction, id_army FROM units INNER JOIN army_units ON army_units.id_units = units.id_units WHERE army_units.id_army = ?", nativeQuery = true)
	Iterable<ArmyUnits> findUnitsByArmy (Long id_army);
	@Query(value = "SELECT units.id_units, units_name, nombre_figurine, points, id_faction FROM units WHERE id_faction = ?", nativeQuery = true)
	Iterable<Units> findUnitsByFaction (Long id_faction);
}