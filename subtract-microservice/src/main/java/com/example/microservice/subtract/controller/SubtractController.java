package com.example.microservice.subtract.controller;

import com.example.microservice.subtract.domain.Subtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractController {

    private Subtractor subtractor;

    public SubtractController(Subtractor subtractor) {
        this.subtractor = subtractor;
    }

    @GetMapping("/")
    @ResponseBody
    public int subtract(@RequestParam int... numbers) {
        return subtractor.subtract(numbers);
    }

}
