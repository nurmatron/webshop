package com.example.webshop.controllers;

import com.example.webshop.models.Employee;
import com.example.webshop.repositories.EmployeeRepository;
import com.example.webshop.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //create
    @PostMapping
    public Employee createEmployee(@RequestBody final Employee employee) {
        return employeeService.create(employee);
    }

    //read1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public Employee getOneEmployee(@PathVariable Integer id) {
        return employeeService.getOne(id);
    }

    //readAll
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    //  Update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public Employee update(@PathVariable Integer id, Employee employee) {
        return employeeService.update(id, employee);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
    }

}