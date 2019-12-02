package com.example.webshop.controllers;

import com.example.webshop.models.Order;
import com.example.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.create(order);
    }

    //readOne
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public Order getOneOrder(@PathVariable Integer id) {
        return orderService.getOne(id);
    }

    //readAll
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    //Udpate
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.delete(id);
    }

}
