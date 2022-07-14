package com.example.fullstackdemo.domain.employee.services;

import com.example.fullstackdemo.domain.core.exceptions.ResourceNotFoundException;
import com.example.fullstackdemo.domain.employee.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> getAll();
    Employee getById(Long id) throws ResourceNotFoundException;
    Employee update(Long id, Employee employeeDetails) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
}
