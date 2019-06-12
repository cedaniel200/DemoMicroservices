package com.example.microservice.superpet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperPetController {

    @GetMapping("/")
    public String getSuperPet() {
        return "Hello World! I am a Super Pet";
    }

}
