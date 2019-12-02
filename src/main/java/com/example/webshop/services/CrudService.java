package com.example.webshop.services;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CrudService<T> {

    T create(T unit);

    T getOne(Integer id);

    List<T> getAll();

    T update(Integer id, T unit);

    void delete(Integer id);

}
