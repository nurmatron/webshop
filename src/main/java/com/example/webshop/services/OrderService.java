package com.example.webshop.services;

import com.example.webshop.models.Order;
import com.example.webshop.models.OrderLine;
import com.example.webshop.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements CrudService<Order> {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> create(Order order) {
        return Optional.of(orderRepository.saveAndFlush(order));
    }

    @Override
    public Optional<Order> getOne(Integer id) {
        return Optional.of(orderRepository.getOne(id));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> update(Integer id, Order order) {
        Order existingOrder = orderRepository.getOne(id);
        BeanUtils.copyProperties(order, existingOrder, "id");
        return Optional.of(orderRepository.saveAndFlush(existingOrder));
    }
    public List<Order>getAllForCustomer(Integer id){
      List<Order> allOrderList =  orderRepository.findAll();
        return allOrderList.stream().filter(order -> order.getId() == id).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        orderRepository.deleteById(id);
        try {
            Order order = orderRepository.getOne(id);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
