package com.gabrielbatista.userregapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.dto.UserDTO;
import com.gabrielbatista.userregapi.dto.UserNewDTO;
import com.gabrielbatista.userregapi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
Endpoints relacionados a classe USER v2
@author gabriel.batista
*/
@Api(value = "Operations related to User registration")
@RestController
@RequestMapping("/v2/user")
public class UserResourceV2 {
	
	@Autowired
	private UserService service;
	
	/**
	Retornaru um usuario para um id informado
	@param id Id do User
	@return Entidade User
	@author gabriel.batista
	*/
	@ApiOperation(value = "Retrieves a user for a given id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id)   {
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	
	}
	
	/**
	Cadastra um usuario
	@param id Id do User
	@exception FieldNotFound
	@return status
	@author gabriel.batista
	*/
	@ApiOperation(value = "Register a user")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UserNewDTO userNewDto) { // @valid to validate through of DTO
		service.validAdress(userNewDto.getAddress());
		User user = service.fromDTO(userNewDto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	/**
	Atualiza um usuario para o id informado
	@param id Id do User
	@return status
	@author gabriel.batista
	*/
	@ApiOperation(value = "Update a user's record for a given id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO userDto, @PathVariable Integer id) {
		service.validAdress(userDto.getAddress());
		User user = service.fromDTO(userDto);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	/**
	Remove um usuario para o id informado
	@param id Id do User
	@return status
	@author gabriel.batista
	*/
	@ApiOperation(value = "Remove a user for a given id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
