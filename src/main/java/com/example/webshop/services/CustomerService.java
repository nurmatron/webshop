package com.example.webshop.services;

import com.example.webshop.models.Article;
import com.example.webshop.models.Customer;
import com.example.webshop.models.Employee;
import com.example.webshop.repositories.ArticleRepository;
import com.example.webshop.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@Service
@SessionScope
public class CustomerService implements CrudService<Customer> {

    private boolean isLoggedin = false;
    private Customer customer;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Optional<Customer> create(Customer customer) {
        return Optional.of(customerRepository.saveAndFlush(customer));
    }

    @Override
    public Optional<Customer> getOne(Integer id) {
        return Optional.of(customerRepository.getOne(id));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> update(Integer id, Customer customer) {
        //TODO: Add validation for each field
        Customer existingCustomer = customerRepository.getOne(id);
        BeanUtils.copyProperties(customer, existingCustomer, "id");
        return Optional.of(customerRepository.saveAndFlush(existingCustomer));
    }

    @Override
    public boolean delete(Integer id) {
        customerRepository.deleteById(id);
        try {
            Customer customer = customerRepository.getOne(id);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean login(String name, String password) {
        isLoggedin = false;
        Optional<Customer> c = customerRepository.findByNameAndPassword(name, password);
        if (c.isPresent()) {
            isLoggedin = true;
            customer = c.get();
        }
        return isLoggedin;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public List<Article> getAllArticles() {

        return articleRepository.findAll();
    }
}
