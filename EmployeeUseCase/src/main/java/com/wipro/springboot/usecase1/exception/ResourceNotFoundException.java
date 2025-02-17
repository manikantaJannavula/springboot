package com.wipro.springboot.usecase1.exception;

// Custom exception class that extends RuntimeException
public class ResourceNotFoundException extends RuntimeException {

    // Constructor to accept the error message when the exception is thrown
    public ResourceNotFoundException(String message) {
        // Call the superclass constructor (RuntimeException) with the provided error message
        super(message);
    }
}
