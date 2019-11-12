package com.gabrielbatista.userregapi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.services.validation.UserUpdate;

@UserUpdate
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Required field")
	private String name;
	
	@Email(message="Invalid Email")
	private String email;
	
	private Date dateBirth;
	
	private String cpf;
	
	public UserDTO() {
		
	}

	public UserDTO(User obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getName();
		this.dateBirth = obj.getDateBirth();
		this.cpf = obj.getCpf();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
