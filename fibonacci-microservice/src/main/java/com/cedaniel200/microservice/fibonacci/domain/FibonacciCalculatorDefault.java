package com.cedaniel200.microservice.fibonacci.domain;

import com.cedaniel200.microservice.fibonacci.domain.model.Fibonacci;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.domain.util.Validator;

public class FibonacciCalculatorDefault implements FibonacciCalculator {

    private final FibonacciRepository fibonacciRepository;
    private final Validator validator;

    public FibonacciCalculatorDefault(FibonacciRepository fibonacciRepository, Validator validator) {
        this.fibonacciRepository = fibonacciRepository;
        this.validator = validator;
    }

    @Override
    public Fibonacci calculateFibonacciOfNumber(int number) {
        return validateAndCalculateFibonacci(number);
    }

    @Override
    public Fibonacci calculateFibonacciOfARandomNumber() {
        int number = getRandomNumer();
        return validateAndCalculateFibonacci(number);
    }

    private int getRandomNumer() {
        return fibonacciRepository.getRandomNumber();
    }

    private Fibonacci validateAndCalculateFibonacci(int number) {
        int result = validator.isNumberValid(number) ? calculateFibonacci(number) : -1;
        return new Fibonacci(number, result);
    }

    private int calculateFibonacci(int number) {
        return number == 0 || number == 1 ? number :
                calculateFibonacci(number - 1) + calculateFibonacci(number - 2);
    }

}
