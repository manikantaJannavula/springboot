package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller will ensure that hte class will control how the object will be
 * used it is done using two parts- 1. get Mapping 2. request parameters
 */
@RestController
public class GreetingController {
	/**
	 * s is a placeholder of run time value for eg: if user inputs :"abcd" result
	 * will be "Hello abcd"
	 */
	private static final String tempLate = "Hello, %s!";
	private final AtomicLong atomicLong = new AtomicLong();// atomicLong is a data type that will handle huge data

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(atomicLong.incrementAndGet(), String.format(tempLate, name));
		
	}
}
