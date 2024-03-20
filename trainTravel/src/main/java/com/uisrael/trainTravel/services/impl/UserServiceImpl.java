package com.uisrael.trainTravel.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisrael.trainTravel.models.entity.buissness.Usuario;
import com.uisrael.trainTravel.models.repository.IUserRepository;
import com.uisrael.trainTravel.services.IUserService;
import com.uisrael.trainTravel.utils.Constants;
import com.uisrael.trainTravel.utils.Utils;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return iUserRepository.findByUsername(username).get();
    }

    public UserDetails save(@ModelAttribute("User") Usuario obj) throws Exception {

        if (iUserRepository.existsById(obj.getId())) {

            throw new Exception(Constants.ERROR_YA_EXISTE);

        }

        obj.setPassword(Utils.getPasswordEncrpt(obj.getPassword()));

        iUserRepository.save(obj);

        return obj;
    }

    public UserDetails update(@ModelAttribute("User") Usuario obj) throws Exception {

        if (iUserRepository.findById(obj.getId()).isEmpty()) {
            throw new Exception("Usuario no existe");
        }

        iUserRepository.save(obj);

        return obj;
    }

    public void deleteById(Integer id) throws Exception {

        if (!iUserRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        iUserRepository.deleteById(id);

    }

    @Override
    public Optional<Usuario> findById(Integer id) {

        Optional<Usuario> obj = iUserRepository.findById(id);

        return obj;
    }

    @Override
    public List<Usuario> getAll() {

        return iUserRepository.findAll();

    }

}
