package com.example.webshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderlines;
    @JsonIgnore
    @ManyToOne
    private Customer customer;
    private boolean expedited;

    public Order() {
    }

    public Order(List<OrderLine> orderlines, Customer customer) {
        this.orderlines = orderlines;
        this.customer = customer;
        this.expedited = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderLine> getOrderlines() {
        if(orderlines == null) {
            orderlines = new ArrayList<>();
        }
        return orderlines;
    }

    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isExpedited() {
        return expedited;
    }

    public void setExpedited(boolean expedited) {
        this.expedited = expedited;
    }
}
