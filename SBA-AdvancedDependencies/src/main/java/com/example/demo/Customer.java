package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a Customer entity in the database.
 * This class is mapped to a table using JPA (Java Persistence API).
 */
@Entity
public class Customer {

    // Primary key for the Customer entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID using database identity column
    private Long id;

    // Customer's first name
    private String firstName;

    // Customer's last name
    private String lastName;

    /**
     * Default constructor with protected access.
     * Required by JPA to create instances via reflection.
     */
    protected Customer() {}

    /**
     * Parameterized constructor to create a Customer object.
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter and Setter methods to access and modify private fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a string representation of the Customer object.
     * This is useful for debugging and logging.
     */
    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
