package com.example.demo;

import java.io.IOException;
import java.net.Socket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TomcatCheckerController {

    public boolean isTomcatRunning() {
        try (Socket socket = new Socket("localhost", 8080)) {
            return true; // Tomcat is running
        } catch (IOException e) {
            return false; // Tomcat is NOT running
        }
    }
    @GetMapping
    public String getName() {
    	return "TomCat is Running";
    }
}
