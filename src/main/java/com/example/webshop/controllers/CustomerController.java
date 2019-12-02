package com.example.webshop.controllers;

import com.example.webshop.models.Customer;
import com.example.webshop.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    //create
    @PostMapping
    public Customer createCustomer(@RequestBody final Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    //get one
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public Customer getOneCustomer(@PathVariable Integer id) {
        return customerRepository.getOne(id);
    }

    //get all
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        //TODO: Add validation for each field
        Customer existingCustomer = customerRepository.getOne(id);
        BeanUtils.copyProperties(customer, existingCustomer, "id");
        return customerRepository.saveAndFlush(existingCustomer);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOneCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }

}
