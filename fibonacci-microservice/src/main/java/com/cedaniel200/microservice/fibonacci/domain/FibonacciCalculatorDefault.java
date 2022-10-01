package com.cedaniel200.microservice.fibonacci.domain;

import com.cedaniel200.microservice.fibonacci.model.Fibonacci;
import com.cedaniel200.microservice.fibonacci.model.ValueRandom;
import com.cedaniel200.microservice.fibonacci.service.RandomService;
import com.cedaniel200.microservice.fibonacci.util.Validator;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FibonacciCalculatorDefault implements FibonacciCalculator {

    private final RandomService service;
    private final Validator validator;

    public FibonacciCalculatorDefault(Retrofit retrofit, Validator validator) {
        this.service = retrofit.create(RandomService.class);
        this.validator = validator;
    }

    @Override
    public Fibonacci calculateFibonacciOfNumber(int number) {
        return validateAndCalculateFibonacci(number);
    }

    @Override
    public Fibonacci calculateFibonacciOfARandomNumber() {
        int number = getRandomNumer();
        return validateAndCalculateFibonacci(number);
    }

    private int getRandomNumer() {
        try {
            Call<ValueRandom> call = service.getRandomNumer(10);
            Response<ValueRandom> response = call.execute();
            return response.body().getValue();
        } catch (Exception e) {
            return -1;
        }
    }

    private Fibonacci validateAndCalculateFibonacci(int number) {
        int result = validator.isNumberValid(number) ? calculateFibonacci(number) : -1;
        return new Fibonacci(number, result);
    }

    private int calculateFibonacci(int number) {
        return number == 0 || number == 1 ? number :
                calculateFibonacci(number - 1) + calculateFibonacci(number - 2);
    }

}
