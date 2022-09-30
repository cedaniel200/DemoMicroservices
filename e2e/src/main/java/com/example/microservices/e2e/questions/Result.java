package com.example.microservices.e2e.questions;

import com.example.microservices.e2e.model.Fibonacci;
import net.serenitybdd.screenplay.Question;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class Result {

    public static Question<Integer> fibonacci() {
        return (actor) -> lastResponse().body().as(Fibonacci.class).getResult();
    }

    public static Question<Boolean> fibonacciRandomIsCorrect() {
        HashMap<Integer, Integer> possibleFibonacciResult = new HashMap<>();
        possibleFibonacciResult.put(0, 0);
        possibleFibonacciResult.put(1, 1);
        possibleFibonacciResult.put(2, 1);
        possibleFibonacciResult.put(3, 2);
        possibleFibonacciResult.put(4, 3);
        possibleFibonacciResult.put(5, 5);
        possibleFibonacciResult.put(6, 8);
        possibleFibonacciResult.put(7, 13);
        possibleFibonacciResult.put(8, 21);
        possibleFibonacciResult.put(9, 34);
        possibleFibonacciResult.put(10, 55);
        return (actor) -> {
            Fibonacci fibonacci = lastResponse().body().as(Fibonacci.class);
            return fibonacci.getResult() == possibleFibonacciResult.get(fibonacci.getNumber());
        };
    }
}
