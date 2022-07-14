package com.example.fullstackdemo.domain.employee.repos;

import com.example.fullstackdemo.domain.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
