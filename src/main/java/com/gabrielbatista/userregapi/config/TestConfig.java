	package com.gabrielbatista.userregapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrielbatista.userregapi.services.DBService;

/**
Configuration for test Profile
@author gabriel.batista
*/
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {		
		dbService.instantiateTestDatabase();
		return true;
	}
	

}
