package com.keyin.airtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirTravelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirTravelApiApplication.class, args);
        System.out.println("Air Travel API is running on http://localhost:8080");
    }
}