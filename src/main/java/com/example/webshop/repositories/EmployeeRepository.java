package com.example.webshop.repositories;

import com.example.webshop.models.Customer;
import com.example.webshop.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByIdAndPassword(Integer id, String password);
}
