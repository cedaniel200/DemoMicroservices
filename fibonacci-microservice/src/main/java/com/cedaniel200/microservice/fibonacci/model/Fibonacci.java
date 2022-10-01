package com.cedaniel200.microservice.fibonacci.model;

public class Fibonacci {
    private final int number;
    private final int result;

    public Fibonacci(int number, int result) {
        this.number = number;
        this.result = result;
    }

    public int getNumber() {
        return number;
    }

    public int getResult() {
        return result;
    }
}
