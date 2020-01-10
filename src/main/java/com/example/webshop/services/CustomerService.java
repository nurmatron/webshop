package com.example.webshop.services;

import com.example.webshop.models.*;
import com.example.webshop.repositories.ArticleRepository;
import com.example.webshop.repositories.CustomerRepository;
import com.example.webshop.repositories.OrderLineRepository;
import com.example.webshop.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@SessionScope
public class CustomerService implements CrudService<Customer> {

    private boolean isLoggedin = false;
    private Customer customer;

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private OrderLineRepository orderLineRepository;
    private OrderService orderService;

    @Autowired
    public void setRepositoriesAndServices(CustomerRepository customerRepository, OrderRepository orderRepository,
                                           OrderLineRepository orderLineRepository, OrderService orderService) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.orderService = orderService;
    }

    @Override
    public Optional<Customer> create(Customer customer) {
        return Optional.of(customerRepository.saveAndFlush(customer));
    }

    @Override
    public Optional<Customer> getOne(Integer id) {
        return Optional.of(customerRepository.getOne(id));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> update(Integer id, Customer customer) {
        //TODO: Add validation for each field
        Customer existingCustomer = customerRepository.getOne(id);
        BeanUtils.copyProperties(customer, existingCustomer, "id");
        return Optional.of(customerRepository.saveAndFlush(existingCustomer));
    }

    @Override
    public boolean delete(Integer id) {
        customerRepository.deleteById(id);
        try {
            Customer customer = customerRepository.getOne(id);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public Customer login(String name, String password) {
        Optional<Customer> c = customerRepository.findByNameAndPassword(name, password);
        if (c.isPresent()) {
            customer = c.get();
            return customer;
        }
        return null;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public boolean checkout(Integer id, List<Article> basket) {
        Optional<Customer> customerOptional = getOne(id);
        AtomicBoolean everythingWentOkay = new AtomicBoolean(true);
        // Borde ändra logiken till CustomerService...
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order customerOrder = new Order();
            customerOrder.setCustomer(customer);
            Optional<Order> optionalOrder = Optional.of(orderRepository.saveAndFlush(customerOrder));
            if (optionalOrder.isPresent()) {
                basket.forEach(article -> {
                    OrderLine orderLine = new OrderLine(article, customerOrder, article.getQuantity());
                    //TODO   There is no check for orderline
                    // CREATE ONE.
                    Optional<OrderLine> optionalOrderLine = Optional.of(orderLineRepository.saveAndFlush(orderLine));
                    if(optionalOrderLine.isPresent()){
                        customerOrder.getOrderlines().add(orderLine);
                    } else {
                        everythingWentOkay.set(false);
                    }
                });
                customer.getOrders().add(customerOrder);
                Optional<Customer> updatedCustomer = update(id, customer);
                if (updatedCustomer.isPresent()) {
                    Optional<Order> updatedOrder = orderService.update(optionalOrder.get().getId(), customerOrder);
                    if(updatedOrder.isPresent() && everythingWentOkay.get()) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
