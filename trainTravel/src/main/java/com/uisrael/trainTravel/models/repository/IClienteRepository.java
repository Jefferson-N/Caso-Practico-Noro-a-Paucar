package com.uisrael.trainTravel.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisrael.trainTravel.models.entity.buissness.Cliente;

@Repository
public interface IClienteRepository  extends JpaRepository<Cliente,Integer>{

    
}
