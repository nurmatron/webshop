package com.example.webshop.controllers;

import com.example.webshop.models.Customer;
import com.example.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController extends SuperController<Customer> {

    @Autowired
    private CustomerService customerService;


    //create
    @PostMapping(path = "/save")
    public ResponseEntity<Customer> createCustomer(@RequestBody final Customer customer) {
        return super.createUnit(customer, customerService);
    }

    //get one
    @GetMapping
    @RequestMapping(path = "/get/{id}")
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
    @RequestMapping(path = "/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        return super.updateUnit(id, customer, customerService);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteOneCustomer(@PathVariable Integer id) {
        return super.deleteUnit(id, customerService);
    }

    //login
    @GetMapping(path = "/customerLogin")
    public String login(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("message", "please login");
        return "customerTemplates/customerLogin";
    }

    @PostMapping(path = "/customerLogin")
    public String submitLogin(@ModelAttribute Customer customer, Model model) {
        String name = customer.getName();
        String password = customer.getPassword();
        if (customerService.login(name, password)) {
            model.addAttribute("customer", customer);
            model.addAttribute("articleList",customerService.getAllArticles());
            return "customerTemplates/customerPage";
        }
        model.addAttribute("message", "Login failed, please try again.");
        return "customerTemplates/customerLogin";
    }


  @PostMapping(path = "/customerPage")
    public String loggedIn(Model model){
        return "customerTemplate/customerPage";
  }



}
