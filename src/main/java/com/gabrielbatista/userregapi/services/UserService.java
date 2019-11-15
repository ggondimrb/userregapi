package com.gabrielbatista.userregapi.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.domain.enums.Genre;
import com.gabrielbatista.userregapi.dto.UserDTO;
import com.gabrielbatista.userregapi.dto.UserNewDTO;
import com.gabrielbatista.userregapi.repositories.UserRepository;
import com.gabrielbatista.userregapi.services.exceptions.FieldNotFoundException;
import com.gabrielbatista.userregapi.services.exceptions.IntegrityConstraintViolationException;
import com.gabrielbatista.userregapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
				
	}
	
	@Transactional
	public User insert(User user) {
		user.setId(null);
		try {
			user = repo.save(user);	
		} catch (Exception e) {
			throw new IntegrityConstraintViolationException("Não é possível inserir usuário com o mesmo cpf e/ou email");
		}
		return user;
	}
	
	public User update(User user) {
		User newUser = find(user.getId());
		updateData(newUser, user);
		return repo.save(user);
	}
	
	private void updateData(User newUser, User user) {		
		if (!user.getName().equals(null)) {
			newUser.setName(user.getName());
		}
		
		if (!user.getGenre().equals(null)) {
			newUser.setGenre(user.getGenre().getCod());
		 }
		 
		if (!user.getDateBirth().equals(null)) {
			newUser.setDateBirth(user.getDateBirth());
		}
		if (!user.getEmail().equals(null)) {
			newUser.setEmail(user.getEmail());
		}
		if (!user.getNationality().equals(null)) {
			newUser.setNationality(user.getNationality());
		}
		if (!user.getNaturalness().equals(null)) {
			newUser.setNaturalness(user.getNaturalness());
		}
		if (!user.getAddress().equals(null)) {
			newUser.setAddress(user.getAddress());
		}
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserNewDTO userDto) {			
		
		User u1 = new User(null, userDto.getName(), Genre.fromDesctoEnum(userDto.getGenre()), userDto.getEmail(), userDto.getDateBirth(), userDto.getNaturalness(),userDto.getNationality(), userDto.getCpf(), userDto.getAddress());					
		
		return u1;
	}
	
	//method to validate required fields  
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(),Genre.fromDesctoEnum(userDto.getGenre()), userDto.getEmail(), userDto.getDateBirth(), userDto.getNaturalness(), userDto.getNationality(), userDto.getCpf(), userDto.getAddress());
	}
	
	public void validAdress(String adress) {
		if (adress.isEmpty()) {
			throw new FieldNotFoundException("O endereço é obrigatório");
		}
	}

}
