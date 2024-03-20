package com.uisrael.trainTravel.services;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.uisrael.trainTravel.models.entity.buissness.Usuario;

public interface IUserService extends UserDetailsService {

    UserDetails save(Usuario obj) throws Exception ;

    void deleteById(Integer id) throws Exception ;

    Optional<Usuario> findById(Integer id);

    UserDetails update(Usuario user) throws Exception;

    List<Usuario> getAll();    

}
