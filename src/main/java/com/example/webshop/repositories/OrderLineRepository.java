package com.example.webshop.repositories;

import com.example.webshop.models.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {
}
