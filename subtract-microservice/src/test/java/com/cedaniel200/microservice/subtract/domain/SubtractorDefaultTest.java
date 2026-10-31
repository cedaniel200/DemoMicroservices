package com.cedaniel200.microservice.subtract.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractorDefaultTest {
    private Subtractor subtractor;

    @BeforeEach
    void setup() {
        subtractor = new SubtractorDefault();
    }

    @Test
    void shouldSubtractTwoPositiveNumber() {
        int result = subtractor.subtract(2, 7);

        assertEquals(-5, result);
    }

    @Test
    void shouldSubtractTwoNegativeNumber() {
        int result = subtractor.subtract(-2, -7);

        assertEquals(5, result);
    }

    @Test
    void shouldSubtractOneNegativeNumberWithOnePositiveNumber() {
        int result = subtractor.subtract(2, -7);

        assertEquals(9, result);
    }

    @Test
    void shouldSubtractTwoZeros() {
        int result = subtractor.subtract(0, 0);

        assertEquals(0, result);
    }
}
