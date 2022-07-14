package com.example.fullstackdemo.domain.employee.services;

import com.example.fullstackdemo.domain.core.exceptions.ResourceNotFoundException;
import com.example.fullstackdemo.domain.employee.models.Employee;
import com.example.fullstackdemo.domain.employee.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return employee;
    }

    @Override
    public Employee update(Long id, Employee employeeDetails) throws ResourceNotFoundException {
        Employee updateEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmail(employeeDetails.getEmail());
        return employeeRepo.save(updateEmployee);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeRepo.delete(employee);
    }
}
