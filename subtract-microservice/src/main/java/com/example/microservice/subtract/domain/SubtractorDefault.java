package com.example.microservice.subtract.domain;

import java.util.Arrays;

public class SubtractorDefault implements Subtractor {
    @Override
    public int subtract(int... numbers) {
        return Arrays.stream(numbers).reduce((left, right) -> left -= right).orElse(0);
    }
}
