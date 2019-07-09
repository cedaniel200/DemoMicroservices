package com.example.microservice.fibonacci.domain;

import com.example.microservice.fibonacci.model.Fibonacci;

public interface FibonacciCalculator {
    Fibonacci calculateFibonacciOfNumber(int number);
    Fibonacci calculateFibonacciOfARandomNumber();
}
