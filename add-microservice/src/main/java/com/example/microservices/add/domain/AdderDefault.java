package com.example.microservices.add.domain;

import java.util.Arrays;

public class AdderDefault implements Adder {
    @Override
    public int add(int... numbers) {
        return Arrays.stream(numbers).reduce((left, right) -> left += right).orElse(0);
    }
}
