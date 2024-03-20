package com.uisrael.trainTravel.services.impl;

import java.util.List;
import java.util.Optional;

import com.uisrael.trainTravel.models.entity.buissness.Cliente;
import com.uisrael.trainTravel.models.repository.IClienteRepository;
import com.uisrael.trainTravel.services.IClienteService;
import com.uisrael.trainTravel.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository iRepository;

    @Override
    public List<Cliente> getAll() {

        List<Cliente> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public Cliente getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Cliente> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public Cliente delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Cliente> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public Cliente save(Cliente t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        

        iRepository.save(t);

        return t;
    }

    @Override
    public Cliente update(Cliente t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
