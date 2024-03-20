package com.uisrael.trainTravel.models.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.uisrael.trainTravel.models.entity.buissness.Usuario;



@Repository
public interface IUserRepository extends JpaRepository<Usuario,Integer> {

    Optional<UserDetails> findByUsername(String obj);

}
