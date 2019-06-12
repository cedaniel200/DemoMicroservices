package com.example.microservices.superhuman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperhumanController {
    @GetMapping("/")
    public String getSuperhuman() {
        return "Hello World! I am a Superhuman";
    }
}
