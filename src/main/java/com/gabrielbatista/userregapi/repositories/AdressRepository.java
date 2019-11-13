package com.gabrielbatista.userregapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbatista.userregapi.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer> {

}