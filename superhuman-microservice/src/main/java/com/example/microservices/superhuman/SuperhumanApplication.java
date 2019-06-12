package com.example.microservices.superhuman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SuperhumanApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperhumanApplication.class, args);
    }
}
