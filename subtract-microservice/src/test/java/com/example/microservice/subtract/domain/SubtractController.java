package com.example.microservice.subtract.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubtractController {
    private Subtractor subtractor;

    @Before
    public void setup() {
        subtractor = new SubtractorDefault();
    }

    @Test
    public void shouldSubtractTwoPositiveNumber() {
        int result = subtractor.subtract(2, 7);

        assertEquals(-5, result);
    }

    @Test
    public void shouldSubtractTwoNegativeNumber() {
        int result = subtractor.subtract(-2, -7);

        assertEquals(5, result);
    }

    @Test
    public void shouldSubtractOneNegativeNumberWithOnePositiveNumber() {
        int result = subtractor.subtract(2, -7);

        assertEquals(9, result);
    }

    @Test
    public void shouldSubtractTwoZeros() {
        int result = subtractor.subtract(0, 0);

        assertEquals(0, result);
    }
}
