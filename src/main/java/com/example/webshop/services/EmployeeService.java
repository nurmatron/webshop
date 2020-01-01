package com.example.webshop.services;

import com.example.webshop.models.Employee;
import com.example.webshop.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements CrudService<Employee> {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Optional<Employee> create(Employee employee) {
        return Optional.of(employeeRepository.saveAndFlush(employee));
    }


    @Override
    public Optional<Employee> getOne(Integer id) {
        return Optional.of(employeeRepository.getOne(id));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }


    @Override
    public Optional<Employee> update(Integer id, Employee employee) {
        //TODO  add validation for each field.
        Employee existingEmployee = employeeRepository.getOne(id);
        BeanUtils.copyProperties(employee, existingEmployee, "id");
        return Optional.of(employeeRepository.saveAndFlush(existingEmployee));
    }

    @Override
    public boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        try {
            Employee employee = employeeRepository.getOne(id);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public Boolean login(Integer id, String password) {
        return employeeRepository.findByIdAndPassword(id, password).isPresent();
    }

}
