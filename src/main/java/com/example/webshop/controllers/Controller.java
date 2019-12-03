package com.example.webshop.controllers;

import com.example.webshop.services.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public abstract class Controller<T> {

    public Controller() {
    }

    public ResponseEntity<T> createUnit(final T unit, CrudService crud) {
        Optional<T> optionalUnit = crud.create(unit);
        if(optionalUnit.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalUnit.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(optionalUnit.get());
        }
    }

    public ResponseEntity<T> getOneUnit(Integer id, CrudService crud) {
        Optional<T> optionalUnit = crud.getOne(id);
        if(optionalUnit.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalUnit.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(optionalUnit.get());
        }
    }

    public ResponseEntity<List<T>> getAllUnits(CrudService crud) {
        List <T> list = crud.getAll();
        if(list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
        }
    }

    public ResponseEntity<String> deleteUnit(Integer id, CrudService crud) {
        if (crud.delete(id)) {
            return ResponseEntity.ok("Delete completed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delete failed");
        }
    }

}
