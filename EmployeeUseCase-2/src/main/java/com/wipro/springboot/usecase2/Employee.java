package com.wipro.springboot.usecase2;

public class Employee {
    // Instance variables to hold the details of the employee
    private int id;            // Unique identifier for each employee
    private String name;       // Name of the employee
    private String role;       // Role of the employee (e.g., Developer, Tester)
    private String designation; // Designation assigned based on the role
    private double salary;     // Salary of the employee

    // Getter and Setter for the 'id' field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for the 'name' field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for the 'role' field
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and Setter for the 'designation' field
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // Getter and Setter for the 'salary' field
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Override 'toString' method to provide a string representation of the employee object
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", designation=" + designation + ", salary="
                + salary + "]";
    }
}
