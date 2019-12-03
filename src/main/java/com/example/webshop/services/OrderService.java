package com.example.webshop.services;

import com.example.webshop.models.Order;
import com.example.webshop.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements CrudService<Order> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order getOne(Integer id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Integer id, Order order) {
        Order existingOrder = getOne(id);
        BeanUtils.copyProperties(order, existingOrder, "id");
        return orderRepository.saveAndFlush(existingOrder);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
