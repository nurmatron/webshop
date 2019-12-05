package com.example.webshop.controllers;

import com.example.webshop.models.Order;
import com.example.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order")
public class OrderController extends Controller<Order>{

    @Autowired
    private OrderService orderService;

    //create
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return super.createUnit(order, orderService);
    }

    //readOne
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<Order> getOneOrder(@PathVariable Integer id) {
        return super.getOneUnit(id, orderService);
    }

    //readAll
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return super.getAllUnits(orderService);
    }

    //Udpate
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return super.updateUnit(id, order, orderService);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id){
        return super.deleteUnit(id, orderService);
    }

}
