package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Iterable<Customer> findByLastName(String lastName);

    Optional<Customer> findById(Long id);  // Fixed return type to Optional<Customer>
}
