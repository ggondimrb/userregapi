package com.gabrielbatista.userregapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.userregapi.domain.Adress;
import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.domain.enums.Genre;
import com.gabrielbatista.userregapi.dto.UserDTO;
import com.gabrielbatista.userregapi.dto.UserNewDTO;
import com.gabrielbatista.userregapi.repositories.UserRepository;
import com.gabrielbatista.userregapi.services.exceptions.DataIntegrityException;
import com.gabrielbatista.userregapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("User not found! Id: " + id + ", Type: " + User.class.getName()));
				
	}
	
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
				
	}
	
	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	private void updateData(User newObj, User obj) {		
		if (!obj.getName().equals(null)) {
			newObj.setName(obj.getName());
		}
		/*
		 * if (!obj.getGenre().equals(null)) { newObj.setGenre(obj.getGenre()); }
		 */
		if (!obj.getDateBirth().equals(null)) {
			newObj.setDateBirth(obj.getDateBirth());
		}
		if (!obj.getEmail().equals(null)) {
			newObj.setEmail(obj.getEmail());
		}
		if (!obj.getNationality().equals(null)) {
			newObj.setNationality(obj.getNationality());
		}
		if (!obj.getNaturalness().equals(null)) {
			newObj.setNaturalness(obj.getNaturalness());
		}
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot exclude why there are related entities"); 
		}
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User fromDTO(UserNewDTO objDto) {			
		
		User u1 = new User(null, objDto.getName(), Genre.toEnum(objDto.getGenre()), objDto.getEmail(), objDto.getDateBirth(), objDto.getNaturalness(),objDto.getNationality(), objDto.getCpf());
		Adress a1 = new Adress(null, objDto.getStreet(), objDto.getNumber(), objDto.getDistrict(), objDto.getZipcode(), objDto.getComplement(),u1);
		
		u1.getAdress().add(a1);	
		
		return u1;
	}
	
	//method to validate required fields  
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(),Genre.toEnum(objDto.getGenre()), objDto.getEmail(), objDto.getDateBirth(), objDto.getNaturalness(), objDto.getNationality(), objDto.getCpf());
	}
}
