package com.cedaniel200.microservice.fibonacci.infrastructure.controller;

import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculator;
import com.cedaniel200.microservice.fibonacci.domain.model.Fibonacci;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fibonacci")
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
