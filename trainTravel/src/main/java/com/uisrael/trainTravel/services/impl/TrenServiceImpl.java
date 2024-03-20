package com.uisrael.trainTravel.services.impl;

import com.uisrael.trainTravel.models.entity.buissness.Tren;
import com.uisrael.trainTravel.models.repository.ITrenRepository;
import com.uisrael.trainTravel.services.ITrenService;
import com.uisrael.trainTravel.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrenServiceImpl implements ITrenService {

    @Autowired
    private ITrenRepository iRepository;

    @Override
    public List<Tren> getAll() {

        List<Tren> generycList = iRepository.findAll();

        return generycList;

    }

    @Override
    public Tren getByID(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Tren> object = iRepository.findById(id);

        return object.get();

    }

    @Override
    public Tren delete(Integer id) throws Exception {

        if (!iRepository.existsById(id)) {
            throw new Exception(Constants.ERROR_NO_EXISTE);

        }

        Optional<Tren> object = iRepository.findById(id);

        iRepository.delete(object.get());

        return object.get();

    }

    @Override
    public Tren save(Tren t) throws Exception {
        if (iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_YA_EXISTE);

        }
        

        iRepository.save(t);

        return t;
    }

    @Override
    public Tren update(Tren t) throws Exception {
        if (!iRepository.existsById(t.getId())) {
            throw new Exception(Constants.ERROR_NO_EXISTE);
        }

        iRepository.save(t);

        return t;
    }

}
