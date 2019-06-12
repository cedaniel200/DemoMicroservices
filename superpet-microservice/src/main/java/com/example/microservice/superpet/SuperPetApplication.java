package com.example.microservice.superpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SuperPetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperPetApplication.class, args);
    }
}
