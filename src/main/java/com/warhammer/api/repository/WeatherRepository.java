package com.warhammer.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.warhammer.api.model.Weather;

public interface WeatherRepository extends CrudRepository<Weather, Long>  {

}
