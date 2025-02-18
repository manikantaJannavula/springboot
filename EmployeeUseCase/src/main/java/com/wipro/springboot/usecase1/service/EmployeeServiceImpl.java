package com.wipro.springboot.usecase1.service;

import com.wipro.springboot.usecase1.exception.ResourceNotFoundException;
import com.wipro.springboot.usecase1.entity.Employee;
import com.wipro.springboot.usecase1.repository.EmployeeRepository;
import com.wipro.springboot.usecase1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Role to designation mapping
    private static final Map<String, String> ROLE_DESIGNATION_MAP = new HashMap<>();

    // Static block to initialize the role to designation mapping
    static {
        ROLE_DESIGNATION_MAP.put("Developer", "Software Developer");
        ROLE_DESIGNATION_MAP.put("Tester", "Quality Analyst");
        ROLE_DESIGNATION_MAP.put("Architect", "Solution Architect");
    }

    // Constructor to inject EmployeeRepository
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Add a new employee and save it to the database
    @Override
    public Employee addEmployee(Employee employee) {
        // Set the designation based on the employee's role
        employee.setDesignation(ROLE_DESIGNATION_MAP.getOrDefault(employee.getRole(), "Employee"));
        // Save the employee to the database
        return employeeRepository.save(employee);
    }

    // Get a list of all employees from the database
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by their ID, or throw a ResourceNotFoundException if not found
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // Update an existing employee's details and save the changes
    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Get the existing employee to update
        Employee existingEmployee = getEmployeeById(id);
        
        // Update employee details
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setDesignation(ROLE_DESIGNATION_MAP.getOrDefault(updatedEmployee.getRole(), "Employee"));
        
        // Save the updated employee
        return employeeRepository.save(existingEmployee);
    }

    // Delete an employee by their ID
    @Override
    public void deleteEmployee(Long id) {
        // Get the employee to delete
        Employee employee = getEmployeeById(id);
        // Delete the employee from the database
        employeeRepository.delete(employee);
    }
}
