package com.wipro.springboot.usecase1.controller;

import com.wipro.springboot.usecase1.entity.Employee;
import com.wipro.springboot.usecase1.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This annotation marks the class as a controller, making it a part of the Spring MVC framework
@RequestMapping("/employees") // This defines the base URL for all the API endpoints in this controller
public class EmployeeController {

    private final EmployeeService employeeService; // Service to handle business logic

    // Constructor injection to inject the EmployeeService into this controller
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST endpoint to add a new employee
    @PostMapping("/add") // Maps the POST request to '/employees/add'
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        // Calls the service layer to add a new employee and returns the employee object as a response
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    // GET endpoint to fetch all employees
    @GetMapping("/all") // Maps the GET request to '/employees/all'
    public ResponseEntity<List<Employee>> getAllEmployees() {
        // Retrieves all employees from the service layer and returns them as a response
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET endpoint to fetch an employee by ID
    @GetMapping("/{id}") // Maps the GET request to '/employees/{id}'
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        // Retrieves a specific employee by ID and returns the employee object as a response
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // PUT endpoint to update an existing employee
    @PutMapping("/update/{id}") // Maps the PUT request to '/employees/update/{id}'
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        // Updates the employee with the provided ID using the updated employee details
        return ResponseEntity.ok(employeeService.updateEmployee(id, updatedEmployee));
    }

    // DELETE endpoint to delete an employee by ID
    @DeleteMapping("/delete/{id}") // Maps the DELETE request to '/employees/delete/{id}'
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        // Calls the service layer to delete the employee by ID and returns a success message
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
