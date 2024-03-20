package com.uisrael.trainTravel.services;

import java.util.List;

public interface IGenerycService<T>  {

    public List<T> getAll();

    public T save(T t) throws Exception;

    public T getByID(Integer id ) throws Exception;

    public T update(T t) throws Exception;

    public T delete(Integer id) throws Exception;


}
