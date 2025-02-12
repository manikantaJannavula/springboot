package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Customer entity.
 * Extends CrudRepository to provide basic CRUD operations.
 */
@Repository  // Marks this interface as a Spring Data repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    /**
     * Finds customers by their last name.
     * @param lastName The last name of the customers to find.
     * @return An iterable list of customers with the given last name.
     */
    Iterable<Customer> findByLastName(String lastName);

    /**
     * Finds a customer by their ID.
     * @param id The unique ID of the customer.
     * @return An Optional containing the customer if found, otherwise empty.
     */
    Optional<Customer> findById(Long id);  // Fixed return type to Optional<Customer>
}
