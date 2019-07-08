package com.example.microservices.add.controller;

import com.example.microservices.add.domain.Adder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    private Adder adder;

    public AddController(Adder adder) {
        this.adder = adder;
    }

    @GetMapping("/")
    @ResponseBody
    public int add(@RequestParam int... numbers) {
        return adder.add(numbers);
    }
}
