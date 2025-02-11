package com.example.demo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTask {

    private final long startTime = System.currentTimeMillis(); // Store the start time

    @Scheduled(fixedRate = 5000) // Runs every 5 seconds
    public void printTime() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Convert to seconds

        System.out.println("Current Time: " + java.time.LocalTime.now() + " | Elapsed Time: " + elapsedTime + "s");

        if (elapsedTime >= 25) { // Stop after 25 seconds
            System.out.println("Stopping execution after 25 seconds.");
            System.exit(0); // Terminates the application
        }
    }
}

