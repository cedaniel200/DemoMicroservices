package com.example.microservice.subtract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubtractApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubtractApplication.class, args);
    }
}
