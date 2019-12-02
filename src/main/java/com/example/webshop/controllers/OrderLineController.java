package com.example.webshop.controllers;

import com.example.webshop.models.OrderLine;
import com.example.webshop.services.OrderLineService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/orderline")
public class OrderLineController {
    @Autowired
    OrderLineService orderLineService;

    //create
    @PostMapping
    public OrderLine createOrderLine(@RequestBody final OrderLine orderLine) {
        return orderLineService.create(orderLine);
    }

    //read1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public OrderLine getOneEmployee(@PathVariable Integer id) {
        return orderLineService.getOne(id);
    }

    //readAll
    @GetMapping
    public List<OrderLine> getAllEmployees() {
        return orderLineService.getAll();
    }

    //  Update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public OrderLine update(@PathVariable Integer id, OrderLine orderLine) {
        return orderLineService.update(id, orderLine);
    }

    //delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOrderLine(@PathVariable Integer id) {
        orderLineService.delete(id);
    }

}

