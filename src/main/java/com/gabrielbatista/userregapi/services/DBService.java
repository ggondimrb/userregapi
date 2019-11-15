package com.gabrielbatista.userregapi.services;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.domain.enums.Genre;
import com.gabrielbatista.userregapi.repositories.UserRepository;

/**
Executa ao iniciar o serviço
@author gabriel.batista
*/
@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		User u1 = new User(null,"Sant da Silva", Genre.MALE, "sds@gmail.com", new Date(), "Baiano","Brasileiro", "31547720069","Rua Prof. José Candido");

		userRepository.save(u1);
		
	}
}
