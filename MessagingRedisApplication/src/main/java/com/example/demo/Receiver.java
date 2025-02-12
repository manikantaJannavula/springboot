package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Receiver class is responsible for handling received messages and 
 * keeping track of the number of messages processed.
 */
public class Receiver {
    
    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    // Atomic counter to keep track of the number of received messages
    private AtomicInteger counter = new AtomicInteger();

    /**
     * This method is invoked when a message is received.
     * It logs the received message and increments the counter.
     *
     * @param message The received message as a String.
     */
    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">"); // Logging the received message
        counter.incrementAndGet(); // Incrementing the counter
    }

    /**
     * Retrieves the current count of received messages.
     *
     * @return The number of messages received so far.
     */
    public int getCount() {
        return counter.get();
    }
}
