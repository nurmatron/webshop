package com.example.webshop.services;

import com.example.webshop.models.Customer;
import com.example.webshop.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CrudService<Customer>{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getOne(Integer id) {
        return customerRepository.getOne(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        //TODO: Add validation for each field
        Customer existingCustomer = customerRepository.getOne(id);
        BeanUtils.copyProperties(customer, existingCustomer, "id");
        return customerRepository.saveAndFlush(existingCustomer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
