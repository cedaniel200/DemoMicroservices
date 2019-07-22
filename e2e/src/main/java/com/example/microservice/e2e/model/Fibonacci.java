package com.example.microservice.e2e.model;

public class Fibonacci {
    private final int number;
    private final int result;

    public Fibonacci(int number, int result) {
        this.number = number;
        this.result = result;
    }

    public int getNumber() {
        return this.number;
    }

    public int getResult() {
        return this.result;
    }
}
