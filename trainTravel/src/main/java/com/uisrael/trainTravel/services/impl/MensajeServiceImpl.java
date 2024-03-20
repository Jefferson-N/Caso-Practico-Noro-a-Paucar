package com.uisrael.trainTravel.services.impl;

import com.uisrael.trainTravel.models.entity.buissness.Mensajes;
import com.uisrael.trainTravel.models.repository.IMensajeRepository;
import com.uisrael.trainTravel.services.IMensajeService;
import com.uisrael.trainTravel.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeServiceImpl implements IMensajeService {

    @Autowired
    private IMensajeRepository iRepository;

    @Override
    public List<Mensajes> getAll() {

        List<Mensajes> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public Mensajes getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Mensajes> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public Mensajes delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Mensajes> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public Mensajes save(Mensajes t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        

        iRepository.save(t);

        return t;
    }

    @Override
    public Mensajes update(Mensajes t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
