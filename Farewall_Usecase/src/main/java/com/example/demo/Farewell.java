package com.example.demo;

/**
 * The Farewell record represents a simple data structure 
 * that holds an ID and a farewell message.
 * 
 * Records in Java are immutable and are mainly used for 
 * storing data without requiring explicit getter, setter, 
 * or constructor implementations.
 * 
 * @param id      A unique identifier for the farewell message.
 * @param message The farewell message content.
 */
public record Farewell(long id, String message) {
}
