package com.warhammer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Annotation informing Spring that this a SpringBoot application
@SpringBootApplication
public class WarhammerApiApplication {

	public static void main(String[] args) {
		// This line simply launches the running of the Spring application.
		SpringApplication.run(WarhammerApiApplication.class, args);
	}
}
