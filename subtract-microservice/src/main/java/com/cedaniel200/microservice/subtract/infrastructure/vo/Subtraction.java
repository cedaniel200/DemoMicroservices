package com.cedaniel200.microservice.subtract.infrastructure.vo;

public class Subtraction {
    private final int[] numbers;
    private final int result;

    public Subtraction(int[] numbers, int result) {
        this.numbers = numbers;
        this.result = result;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getResult() {
        return result;
    }
}
