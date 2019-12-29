package com.example.webshop.controllers;

import com.example.webshop.models.Article;
import com.example.webshop.models.Customer;
import com.example.webshop.models.Order;
import com.example.webshop.models.OrderLine;
import com.example.webshop.services.CustomerService;
import com.example.webshop.services.OrderLineService;
import com.example.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController extends SuperController<Customer> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderLineService orderLineService;

    //create
    @PostMapping(path = "save")
    public ResponseEntity<Customer> createCustomer(@RequestBody final Customer customer) {
        Optional<Customer> optionalUnit = customerService.create(customer);
        if (optionalUnit.isPresent()) {
            return ResponseEntity.ok().body(optionalUnit.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(optionalUnit.get());
        }
    }

    //get one
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable Integer id) {
        Optional<Customer> optionalUnit = customerService.getOne(id);
        if (optionalUnit.isPresent()) {
            return ResponseEntity.ok().body(optionalUnit.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(optionalUnit.get());
        }
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
    public void deleteOneCustomer(@PathVariable Integer id) {
        super.deleteUnit(id, customerService);
    }

    //login
    @GetMapping(path = "login/{name}/{password}")
    public Customer login(@PathVariable String name, @PathVariable String password) {
        return customerService.login(name, password);
    }

    //create order from basket
    @PostMapping(path = "/checkout/{id}")
    public boolean checkout(@PathVariable Integer id, @RequestBody List<Article> basket) {
        Optional<Customer> customerOptional = customerService.getOne(id);
        // Borde ändra logiken till CustomerService...
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order customerOrder = new Order();
            System.out.println(customer.getId() + " i am customer id");
            customerOrder.setCustomer(customer);
            Optional<Order> optionalOrder = orderService.create(customerOrder);
            if (optionalOrder.isPresent()) {
                basket.forEach(article -> {
                    OrderLine orderLine = new OrderLine(article, customerOrder, article.getQuantity());
                    //TODO   There is no check for orderline
                    // CREATE ONE.
                    orderLineService.create(orderLine);
                    customerOrder.getOrderlines().add(orderLine);
                });
                customer.getOrders().add(customerOrder);
                Optional<Customer> updatedCustomer = customerService.update(id, customer);
                if (updatedCustomer.isPresent()) {
                    orderService.create(customerOrder);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    //see all articles


}
