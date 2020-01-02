package com.example.webshop.controllers;

import com.example.webshop.models.Order;
import com.example.webshop.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping(path = "api/order")
public class OrderController extends SuperController<Order> {

    @Autowired
    private OrderService orderService;

    //create
    @PostMapping(path = "save")
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

    @GetMapping(path = "/getall/{id}")
    public ResponseEntity<List<Order>> getOrdersForCustomer(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.getAllForCustomer(id));
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
    public void deleteOrder(@PathVariable Integer id) {
        super.deleteUnit(id, orderService);
    }

    @PutMapping
    @RequestMapping(path = "expedite/{id}")
    public ResponseEntity<Order> expediteOrder(@PathVariable Integer id){
     Optional<Order> order = orderService.getOne(id);
        if (order.isPresent()){
            order.get().setExpedited(true);
            return super.updateUnit(id, order.get(), orderService);
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

