package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.warhammer.api.model.Weather;
import com.warhammer.api.service.WeatherService;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/weathers")
	public Iterable<Weather> getWeather() {
		return weatherService.getWeather();
	}
	
	@GetMapping("/weathers/{id}")
	public Weather getWeather(@PathVariable("id") final Long id){
		Optional<Weather> weather = weatherService.getWeather(id);
		if(weather.isPresent()) {
			return weather.get();
		} else {
			return null;
		}
	}
	@PostMapping("/weathers")
	public Weather createWeather(@RequestBody Weather weather) {
		return weatherService.saveWeather(weather);
	}
	@PutMapping("/weathers/{id}")
	public Weather updateWeather(@PathVariable("id") final Long id, @RequestBody Weather weather) {
		Optional<Weather> e = weatherService.getWeather(id);
		if(e.isPresent()) {
			Weather currentWeather = e.get();
			
			String weatherName = weather.getWeatherName();
			if(weatherName != null) {
				currentWeather.setWeatherName(weatherName);
			}
			weatherService.saveWeather(currentWeather);
			return currentWeather;
		} else {
			return null;
		}
	}
	@PatchMapping("/weathers/{id}")
	public Weather patchWeather(@PathVariable("id") final Long id, @RequestBody Weather weather){
		Optional<Weather> e = weatherService.getWeather(id);
		if(e.isPresent()) {
			Weather currentWeather = e.get();
			
			String weatherName = weather.getWeatherName();
			if(weatherName != null) {
				currentWeather.setWeatherName(weatherName);
				weatherService.saveWeather(currentWeather);
			return currentWeather;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/weathers/{id}")
	public void deleteWeather(@PathVariable("id") final Long id) {
		weatherService.deleteWeather(id);
	}
}
