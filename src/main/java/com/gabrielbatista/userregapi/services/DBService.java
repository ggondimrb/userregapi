package com.gabrielbatista.userregapi.services;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbatista.userregapi.domain.Adress;
import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.domain.enums.Genre;
import com.gabrielbatista.userregapi.repositories.AdressRepository;
import com.gabrielbatista.userregapi.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		

		User u1 = new User(null,"Sant da Silva", Genre.toEnum("M"), "sds@gmail.com", new Date(), "Bahiano","Brasileiro", "31547720069");

		userRepository.save(u1);
		
		Adress a1 = new Adress(null,"Rua Professor Jose Candido Pessoa", 1448, "Bairro Novo",53030020, "Ao lado da Rua Augusta",u1);
		
		adressRepository.save(a1);
		
		
	}
}
