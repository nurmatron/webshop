package com.example.webshop.controllers;

import com.example.webshop.models.Employee;
import com.example.webshop.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController extends SuperController<Employee> {
    @Autowired
    EmployeeService employeeService;

    //create
    @PostMapping(path = "save")
    public ResponseEntity<Employee> createEmployee(@RequestBody final Employee employee) {
        return super.createUnit(employee, employeeService);
    }
    //read1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable Integer id) {
        return super.getOneUnit(id, employeeService);
    }


    //readAll
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return super.getAllUnits(employeeService);
    }


    // update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return super.updateUnit(id, employee, employeeService);
    }


    // delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteOneEmployee(@PathVariable Integer id) {
        return super.deleteUnit(id, employeeService);
    }

}