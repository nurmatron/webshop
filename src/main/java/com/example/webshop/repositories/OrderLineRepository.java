package com.example.webshop.repositories;

import com.example.webshop.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
