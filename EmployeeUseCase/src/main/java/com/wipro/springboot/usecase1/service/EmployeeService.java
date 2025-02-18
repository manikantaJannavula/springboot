package com.wipro.springboot.usecase1.service;

import com.wipro.springboot.usecase1.entity.Employee;
import java.util.List;

// Service interface for Employee-related operations
public interface EmployeeService {

    // Method to add a new employee
    Employee addEmployee(Employee employee);

    // Method to get a list of all employees
    List<Employee> getAllEmployees();

    // Method to get an employee by their ID
    Employee getEmployeeById(Long id);

    // Method to update an existing employee's details
    Employee updateEmployee(Long id, Employee updatedEmployee);

    // Method to delete an employee by their ID
    void deleteEmployee(Long id);
}
