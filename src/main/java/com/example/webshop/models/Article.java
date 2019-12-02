package com.example.webshop.models;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToOne(mappedBy = "article")
    private OrderLine orderline;

    public Article() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderLine getOrderline() {
        return orderline;
    }

    public void setOrderline(OrderLine orderline) {
        this.orderline = orderline;
    }
}
