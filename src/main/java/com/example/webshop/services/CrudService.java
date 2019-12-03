package com.example.webshop.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CrudService<T> {

    Optional<T> create(T unit);

    Optional<T> getOne(Integer id);

    List<T> getAll();

    Optional<T> update(Integer id, T unit);

    boolean delete(Integer id);

}
