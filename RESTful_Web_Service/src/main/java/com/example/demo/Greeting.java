package com.example.demo;

/**
 * The Greeting record represents an immutable data structure
 * that holds an ID and a greeting message.
 * 
 * Records in Java (introduced in Java 14) are primarily used
 * for storing data without requiring explicit getter, setter,
 * or constructor implementations.
 * 
 * @param id      A unique identifier for the greeting message.
 * @param content The greeting message content.
 */
public record Greeting(long id, String content) {
    // No additional methods or constructors are needed as records 
    // automatically generate constructors, getters, equals(), 
    // hashCode(), and toString() methods.
}
