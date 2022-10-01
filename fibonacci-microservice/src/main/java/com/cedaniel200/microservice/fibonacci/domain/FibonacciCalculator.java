package com.cedaniel200.microservice.fibonacci.domain;

import com.cedaniel200.microservice.fibonacci.model.Fibonacci;

public interface FibonacciCalculator {
    Fibonacci calculateFibonacciOfNumber(int number);
    Fibonacci calculateFibonacciOfARandomNumber();
}
