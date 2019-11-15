package com.gabrielbatista.userregapi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
	
	private String genre;
	
	private String nationality;
	
	private String naturalness;
	
	private String address;
	
	public UserDTO() {
		
	}
	

	/*
	 * public UserDTO(User obj) { super(); this.id = obj.getId(); this.name =
	 * obj.getName(); this.email = obj.getEmail(); this.dateBirth =
	 * obj.getDateBirth(); this.cpf = obj.getCpf(); this.genre =
	 * Genre.toEnum(obj.getGenre()); this.nationality = obj.getNationality();
	 * this.naturalness = obj.getNaturalness(); }
	 */
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

	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNaturalness() {
		return naturalness;
	}

	public void setNaturalness(String naturalness) {
		this.naturalness = naturalness;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
}
