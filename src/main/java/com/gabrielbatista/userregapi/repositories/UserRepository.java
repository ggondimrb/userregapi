package com.gabrielbatista.userregapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.userregapi.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	  @Transactional(readOnly=true) 
	  User findByEmail(String email);
	  
	  @Transactional(readOnly=true) 
	  User findByCpf(String cpf);
	 
	
}
