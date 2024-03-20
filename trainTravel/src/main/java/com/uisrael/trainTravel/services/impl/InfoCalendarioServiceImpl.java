package com.uisrael.trainTravel.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisrael.trainTravel.models.entity.buissness.InfoCalendario;
import com.uisrael.trainTravel.models.repository.IInfoCalendarioRepository;
import com.uisrael.trainTravel.services.IInfoCalendarioService;
import com.uisrael.trainTravel.utils.Constants;

@Service
public class InfoCalendarioServiceImpl implements IInfoCalendarioService {

    @Autowired
    private IInfoCalendarioRepository iRepository;

    @Override
    public List<InfoCalendario> getAll() {

        List<InfoCalendario> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public InfoCalendario getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<InfoCalendario> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public InfoCalendario delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<InfoCalendario> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public InfoCalendario save(InfoCalendario t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        
        iRepository.save(t);

        return t;
    }

    @Override
    public InfoCalendario update(InfoCalendario t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
