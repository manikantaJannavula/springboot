package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The FarewellController class is a REST controller that provides
 * an endpoint to return a farewell message.
 */
@RestController
public class FarewellController {

    // Template string used to format farewell messages
    private static final String template = "Goodbye, %s!";
    
    // AtomicLong to generate a unique ID for each farewell message
    private final AtomicLong counter = new AtomicLong();

    /**
     * Handles GET requests to "/farewell-Day" and returns a Farewell object.
     * 
     * @param name The name to include in the farewell message (default: "Friends").
     * @return A Farewell object containing a unique ID and a formatted message.
     */
    @GetMapping("/farewell-Day")
    public Farewell farewell(@RequestParam(value = "name", defaultValue = "Friends") String name) {
        return new Farewell(counter.incrementAndGet(), String.format(template, name));
    }
}
