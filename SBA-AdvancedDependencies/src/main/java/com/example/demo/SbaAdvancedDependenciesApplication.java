package com.example.demo;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for managing Customer entities using Spring Boot.
 */
@SpringBootApplication(scanBasePackages = {"entity", "repository", "controller"}) // Scans specified packages for Spring components
public class SbaAdvancedDependenciesApplication {

    // Logger for displaying messages in the console
    private static final Logger log = LoggerFactory.getLogger(SbaAdvancedDependenciesApplication.class);

    /**
     * Main method to run the Spring Boot application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SbaAdvancedDependenciesApplication.class, args);
    }

    /**
     * A CommandLineRunner bean to execute database operations after the application starts.
     * @param repository The CustomerRepository to interact with the database.
     * @return A CommandLineRunner instance.
     */
    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // Save a few customers into the database
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // Fetch all customers and print them
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> log.info(customer.toString()));

            log.info("");

            // Fetch an individual customer by ID
            Optional<Customer> customer = repository.findById(1L);
            if (customer.isPresent()) {
                log.info("Customer found with findById(1L):");
                log.info("--------------------------------");
                log.info(customer.get().toString());
            } else {
                log.info("Customer with ID 1 not found");
            }

            log.info("");

            // Fetch customers by last name
            log.info("Customer(s) found with findByLastName('Bauer'):");
            
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
        };
    }
}
