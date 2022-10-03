package com.cedaniel200.microservices.e2e.model;

public class Fibonacci {
    private int number;
    private int result;

    public Fibonacci() {
    }

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
