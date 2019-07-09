package com.example.microservice.fibonacci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FibonacciApplication {
    public static void main(String[] args) {
        SpringApplication.run(FibonacciApplication.class, args);
    }
}
