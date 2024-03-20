package com.uisrael.trainTravel.services.impl;

import com.uisrael.trainTravel.models.entity.buissness.Reserva;
import com.uisrael.trainTravel.models.repository.IReservaRepository;
import com.uisrael.trainTravel.services.IReservaService;
import com.uisrael.trainTravel.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaRepository iRepository;

    @Override
    public List<Reserva> getAll() {

        List<Reserva> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public Reserva getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Reserva> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public Reserva delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Reserva> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public Reserva save(Reserva t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        

        iRepository.save(t);

        return t;
    }

    @Override
    public Reserva update(Reserva t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
