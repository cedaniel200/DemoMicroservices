package com.example.microservices.add.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdderDefaultTest {
    private Adder adder;

    @Before
    public void setup(){
        adder = new AdderDefault();
    }

    @Test
    public void shouldAddTwoPositiveNumber(){
        int result = adder.add(2, 7);

        assertEquals(9, result);
    }

    @Test
    public void shouldAddTwoNegativeNumber(){
        int result = adder.add(-2, -7);

        assertEquals(-9, result);
    }

    @Test
    public void shouldAddOneNegativeNumberWithOnePositiveNumber(){
        int result = adder.add(2, -7);

        assertEquals(-5, result);
    }

    @Test
    public void shouldAddTwoZeros(){
        int result = adder.add(0, 0);

        assertEquals(0, result);
    }
}
