package com.example.microservice.fibonacci.controller;

import com.example.microservice.fibonacci.domain.FibonacciCalculator;
import com.example.microservice.fibonacci.model.Fibonacci;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

    private FibonacciCalculator fibonacciCalculator;

    public FibonacciController(FibonacciCalculator fibonacciCalculator) {
        this.fibonacciCalculator = fibonacciCalculator;
    }

    @GetMapping("/")
    @ResponseBody
    public Fibonacci calculateFibonacciOfNumber(@RequestParam int number) {
        return fibonacciCalculator.calculateFibonacciOfNumber(number);
    }

    @GetMapping("/random")
    @ResponseBody
    public Fibonacci calculateFibonacciOfARandomNumber() {
        return fibonacciCalculator.calculateFibonacciOfARandomNumber();
    }

}
