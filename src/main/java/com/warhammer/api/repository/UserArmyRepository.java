package com.warhammer.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.UserArmy;

public interface UserArmyRepository extends CrudRepository<UserArmy, Long>{
	@Query(value="SELECT id_army, army_name,id_user FROM army WHERE id_user = ?", nativeQuery = true)
	public Iterable<UserArmy> findByIdUser(final Long id);
}
