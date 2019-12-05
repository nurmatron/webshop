package com.example.webshop.controllers;

import com.example.webshop.models.Article;
import com.example.webshop.models.Customer;
import com.example.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController extends Controller<Customer> {

    @Autowired
    private CustomerService customerService;



    //create
    @PostMapping(path = "save")
    public ResponseEntity<Customer> createCustomer(@RequestBody final Customer customer) {
        return super.createUnit(customer, customerService);
    }

    //get one
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable Integer id) {
        return super.getOneUnit(id, customerService);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return super.getAllUnits(customerService);
    }

    //update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        return super.updateUnit(id, customer, customerService);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteOneCustomer(@PathVariable Integer id) {
        return super.deleteUnit(id, customerService);
    }

    //login
    @GetMapping(path = "login/{name}/{password}")
    public String login(@PathVariable String name, @PathVariable String password) {
        if (customerService.login(name, password)) {
            return "loggedin";
        } else {
            return "login";
        }
    }

    @GetMapping(path = "loggedin") // byt eventuellt denna
    public ResponseEntity<List<Article>> showAllArticles(){
        if (!customerService.isLoggedin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok().body(customerService.getAllArticles());
    }


    //add to basket


    //create order from basket


    //see all articles


}
