package com.cedaniel200.microservices.add.infrastructure.vo;

public class Sum {
    private final int[] numbers;
    private final int result;

    public Sum(int[] numbers, int result) {
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
