package com.example.webshop.services;

import com.example.webshop.models.OrderLine;
import com.example.webshop.repositories.OrderLineRepository;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLineService implements CrudService<OrderLine> {
    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public OrderLine create(OrderLine orderLine) {
        return orderLineRepository.saveAndFlush(orderLine);
    }

    @Override
    public OrderLine getOne(Integer id) {
        return orderLineRepository.getOne(id);
    }

    @Override
    public List<OrderLine> getAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine update(Integer id, OrderLine orderLine) {
        OrderLine existingOrderLine = orderLineRepository.getOne(id);
        BeanUtils.copyProperties(orderLine, existingOrderLine, "id");
        return orderLineRepository.saveAndFlush(existingOrderLine);
    }

    @Override
    public void delete(Integer id) {
        orderLineRepository.deleteById(id);
    }
}
