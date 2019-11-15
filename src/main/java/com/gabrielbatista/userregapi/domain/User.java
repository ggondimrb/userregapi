package com.gabrielbatista.userregapi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielbatista.userregapi.domain.enums.Genre;

@Entity
	public class User implements Serializable {	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		
		private String name;
		
		private Integer genre;
		
		@Column(unique=true)
		private String email;
	
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date dateBirth;	
		
		private String naturalness;
		
		private String nationality;
		
		@Column(unique=true)
		private String cpf;
		
		private String address;
		
		public User() {
			
		}
	
		public User(Integer id, String name, Genre genre, String email, Date dateBirth, String naturalness,
				String nationality, String cpf, String address) {
			super();
			this.id = id;
			this.name = name;
			this.genre = (genre == null ? null : genre.getCod());
			this.email = email;
			this.dateBirth = dateBirth;
			this.naturalness = naturalness;
			this.nationality = nationality;
			this.cpf = cpf;
			this.address = address;
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
	
		public void setName(String name) {
			this.name = name;
		}
	
		public Genre getGenre() {
			return Genre.toEnum(genre);
		}

		public void setGenre(Integer genre) {
			this.genre = genre;
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
	
		public String getNaturalness() {
			return naturalness;
		}
	
		public void setNaturalness(String naturalness) {
			this.naturalness = naturalness;
		}
	
		public String getNationality() {
			return nationality;
		}
	
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
	
		public String getCpf() {
			return cpf;
		}
	
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}		

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}	
	}
