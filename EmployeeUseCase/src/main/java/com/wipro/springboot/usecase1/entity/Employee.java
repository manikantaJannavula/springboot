package com.wipro.springboot.usecase1.entity;

import jakarta.persistence.*;

// Marks the class as a JPA entity representing a database table
@Entity
// Specifies the name of the table in the database to which this entity will be mapped
@Table(name = "employees")
public class Employee {

    // Marks the 'id' field as the primary key for the entity
    @Id
    // Specifies that the value of the 'id' field will be automatically generated by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Field to store the name of the employee
    private String name;

    // Field to store the role of the employee
    private String role;

    // Field to store the designation of the employee
    private String designation;

    // Default constructor (required by JPA)
    public Employee() {
    }

    // Constructor to initialize the 'name' and 'role' fields
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Getter and Setter methods for 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for 'role'
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and Setter methods for 'designation'
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
