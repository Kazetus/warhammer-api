package com.warhammer.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Edition;

public interface EditionRepository extends CrudRepository<Edition, Long>  {

}
