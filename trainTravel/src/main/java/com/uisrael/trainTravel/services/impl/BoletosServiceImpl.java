package com.uisrael.trainTravel.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.trainTravel.models.entity.buissness.Boletos;
import com.uisrael.trainTravel.models.repository.IBoletosRepository;
import com.uisrael.trainTravel.services.IBoletosService;
import com.uisrael.trainTravel.utils.Constants;

@Service
public class BoletosServiceImpl implements IBoletosService {

    @Autowired
    private IBoletosRepository iRepository;

    @Override
    public List<Boletos> getAll() {

        List<Boletos> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public Boletos getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Boletos> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public Boletos delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Boletos> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public Boletos save(Boletos t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        

        iRepository.save(t);

        return t;
    }

    @Override
    public Boletos update(Boletos t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
