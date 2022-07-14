package com.example.fullstackdemo.domain.employee.service;

import com.example.fullstackdemo.domain.core.exceptions.ResourceNotFoundException;
import com.example.fullstackdemo.domain.employee.models.Employee;
import com.example.fullstackdemo.domain.employee.repos.EmployeeRepo;
import com.example.fullstackdemo.domain.employee.services.EmployeeService;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepo employeeRepo;

    @Test
    public void createTest01() {
        Employee employee = new Employee(1, "mckenna", "ohara", "email");
        BDDMockito.doReturn(employee).when(employeeRepo).save(employee);
        Employee actual = employeeService.create(employee);
        Assertions.assertEquals(employee, actual);
    }

    @Test
    public void getAllTest01() {
        Employee employee = new Employee(1, "mckenna", "ohara", "email");
        List<Employee> employees = new ArrayList<>();
        List<Employee> actual = new ArrayList<>();
        BDDMockito.doReturn(employee).when(employeeRepo).save(employee);
        BDDMockito.doReturn(employees).when(employeeRepo).findAll();
        actual = employeeService.getAll();
        Assertions.assertEquals(employees, actual);
    }

    @Test
    public void getByIdTest01() {
        Employee employee = new Employee(1, "mckenna", "ohara", "email");
        BDDMockito.doReturn(employee).when(employeeRepo).save(employee);
        BDDMockito.doReturn(Optional.of(employee)).when(employeeRepo).findById(1L);
        Employee actual = employeeService.getById(1L);
        Assertions.assertEquals(employee, actual);
    }

    @Test
    public void getByIdTest02() {
        BDDMockito.doReturn(Optional.empty()).when(employeeRepo).findById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getById(1L);
        });
    }

    @Test
    public void updateTest01() {
        Employee employee = new Employee(1, "mckenna", "ohara", "email");
        BDDMockito.doReturn(employee).when(employeeRepo).save(employee);
        employee.setFirstName("bob");
        employee.setLastName("smith");
        employee.setEmail("email");
        BDDMockito.doReturn(employee).when(employeeRepo).save(employee);
        Employee actual = employeeService.update(1L, employee);
        Assertions.assertEquals(employee, actual);
    }
}
