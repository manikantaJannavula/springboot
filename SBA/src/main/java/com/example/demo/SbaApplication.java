package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbaApplication.class, args);

        // Check Tomcat Status After Spring Boot Starts
        TomcatCheckerController checker = new TomcatCheckerController();
        if (checker.isTomcatRunning()) {
            System.out.println("Tomcat is running on port 8080.");
        } else {
            System.out.println("Tomcat is NOT running.");
        }
        
        
        
        
        
    }
}
