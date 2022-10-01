package com.cedaniel200.microservices.add.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AdderDefaultTest {
    private Adder adder;

    @BeforeEach
    public void setup(){
        adder = new AdderDefault();
    }

    @Test
    void shouldAddTwoPositiveNumber(){
        int result = adder.add(2, 7);

        assertEquals(9, result);
    }

    @Test
    void shouldAddTwoNegativeNumber(){
        int result = adder.add(-2, -7);

        assertEquals(-9, result);
    }

    @Test
    void shouldAddOneNegativeNumberWithOnePositiveNumber(){
        int result = adder.add(2, -7);

        assertEquals(-5, result);
    }

    @Test
    void shouldAddTwoZeros(){
        int result = adder.add(0, 0);

        assertEquals(0, result);
    }
}
