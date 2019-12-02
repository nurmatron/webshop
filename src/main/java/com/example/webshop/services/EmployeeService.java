package com.example.webshop.services;

import com.example.webshop.models.Employee;
import com.example.webshop.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee> {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee unit) {
        return employeeRepository.saveAndFlush(unit);
    }

    @Override
    public Employee getOne(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Integer id, Employee unit) {
        //TODO  add validation for each field.
        Employee existingEmployee = employeeRepository.getOne(id);
        BeanUtils.copyProperties(unit, existingEmployee, "id");
        return employeeRepository.saveAndFlush(existingEmployee);
    }


    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }


}
