package com.uisrael.trainTravel.services.impl;

import com.uisrael.trainTravel.models.entity.buissness.DetalleViaje;
import com.uisrael.trainTravel.models.repository.IDetalleViajeRepository;
import com.uisrael.trainTravel.services.IDetalleViajeService;
import com.uisrael.trainTravel.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleViajeServiceImpl implements IDetalleViajeService {

    @Autowired
    private IDetalleViajeRepository iRepository;

    @Override
    public List<DetalleViaje> getAll() {

        List<DetalleViaje> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public DetalleViaje getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<DetalleViaje> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public DetalleViaje delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<DetalleViaje> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public DetalleViaje save(DetalleViaje t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        
        // t.setEcomerce(List.of(new Ecomerce()));

        iRepository.save(t);

        return t;
    }

    @Override
    public DetalleViaje update(DetalleViaje t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
