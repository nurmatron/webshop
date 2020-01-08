package com.example.webshop.services;

import com.example.webshop.models.OrderLine;
import com.example.webshop.repositories.OrderLineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderLineService implements CrudService<OrderLine> {
    private OrderLineRepository orderLineRepository;

    @Autowired
    public void setOrderLineRepository(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }


    @Override
    public Optional<OrderLine> create(OrderLine orderLine) {
        return Optional.of(orderLineRepository.saveAndFlush(orderLine));
    }

    @Override
    public Optional<OrderLine> getOne(Integer id) {
        return Optional.of(orderLineRepository.getOne(id));
    }

    @Override
    public List<OrderLine> getAll() {
        return orderLineRepository.findAll();
    }

    public List<OrderLine> getAllForOrder(Integer id) {
        List<OrderLine> allOrderLines = orderLineRepository.findAll();
        return allOrderLines.stream().filter(orderLine -> orderLine.getOrder().getId() == id).collect(Collectors.toList());
    }


    @Override
    public Optional<OrderLine> update(Integer id, OrderLine orderLine) {
        //TODO  add validation for each field.
        OrderLine existingOrderLine = orderLineRepository.getOne(id);
        BeanUtils.copyProperties(orderLine, existingOrderLine, "id");
        return Optional.of(orderLineRepository.saveAndFlush(existingOrderLine));
    }

    @Override
    public boolean delete(Integer id) {
        orderLineRepository.deleteById(id);
        try {
            OrderLine orderLine = orderLineRepository.getOne(id);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
