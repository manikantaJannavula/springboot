package com.example.demo;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"entity", "repository", "controller"})
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomerController.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // Save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // Fetch all customers
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
