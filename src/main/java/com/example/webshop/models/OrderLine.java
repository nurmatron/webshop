package com.example.webshop.models;

import com.example.webshop.controllers.Controller;

import javax.persistence.*;

@Entity
@Table(name = "orderlines")
public class OrderLine  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Article article;
    @ManyToOne
    private Order order;
    private int quantity;
     public OrderLine() {}

    public OrderLine(Article article, Order order, int quantity) {
        this.article = article;
        this.order = order;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
