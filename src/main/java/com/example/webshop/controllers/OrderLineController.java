package com.example.webshop.controllers;

import com.example.webshop.models.Order;
import com.example.webshop.models.OrderLine;
import com.example.webshop.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/orderline")
public class OrderLineController extends SuperController<OrderLine> {
    @Autowired
    OrderLineService orderLineService;

    //create
    @PostMapping(path = "save")
    public ResponseEntity<OrderLine> createOrderLine(@RequestBody final OrderLine orderLine) {
        return super.createUnit(orderLine, orderLineService);
    }

    //read1
    @GetMapping
    @RequestMapping(path = "get/{id}")
    public ResponseEntity<OrderLine> getOneOrderLine(@PathVariable Integer id) {
        return super.getOneUnit(id, orderLineService);
    }

    @GetMapping(path = "/getall/{id}")
    public ResponseEntity<List<OrderLine>> getOrderLinesForOrder(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderLineService.getAllForOrder(id));
    }

    //readAll
    @GetMapping
    public ResponseEntity<List<OrderLine>> getAllOrderLines() {
        return super.getAllUnits(orderLineService);
    }

    // update
    @PutMapping
    @RequestMapping(path = "update/{id}")
    public ResponseEntity<OrderLine> updateOrderLine(@PathVariable Integer id, @RequestBody OrderLine orderLine) {
        return super.updateUnit(id, orderLine, orderLineService);
    }


    // delete
    @DeleteMapping
    @RequestMapping(path = "delete/{id}")
    public void deleteOneOrderLine(@PathVariable Integer id) {
        super.deleteUnit(id, orderLineService);
    }

}

