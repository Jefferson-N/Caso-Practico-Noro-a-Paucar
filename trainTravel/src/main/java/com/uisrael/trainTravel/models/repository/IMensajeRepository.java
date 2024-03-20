package com.uisrael.trainTravel.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisrael.trainTravel.models.entity.buissness.Mensajes;


@Repository
public interface IMensajeRepository extends JpaRepository<Mensajes,Integer>{


}
