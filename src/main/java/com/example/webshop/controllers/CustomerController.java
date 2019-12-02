package com.example.webshop.controllers;

import com.example.webshop.models.Customer;
import com.example.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //create
    @PostMapping
    public Customer createCustomer(@RequestBody final Customer customer) {
        return customerService.create(customer);
    }

    //get one
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public Customer getOneCustomer(@PathVariable Integer id) {
        return customerService.getOne(id);
    }

    //get all
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    //update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        return customerService.update(id, customer);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOneCustomer(@PathVariable Integer id) {
        customerService.delete(id);
    }

}
