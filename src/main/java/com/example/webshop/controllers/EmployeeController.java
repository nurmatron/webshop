package com.example.webshop.controllers;

import com.example.webshop.models.Employee;
import com.example.webshop.repositories.EmployeeRepository;
import com.example.webshop.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
@CrossOrigin(origins = "http://localhost:4200")
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
    public void deleteOneEmployee(@PathVariable Integer id) {
        super.deleteUnit(id, employeeService);
    }


    @GetMapping(path = "login/{id}/{password}")
    public ResponseEntity<Boolean> loginEmployee(@PathVariable Integer id, @PathVariable String password) {
        boolean loggedIn = employeeService.login(id, password);
        if (loggedIn) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}

