package com.example.microservice.fibonacci.util;

public class ValidatorDefault implements Validator {
    @Override
    public boolean isNumberValid(int number){
        return number >= 0 && number <= 10;
    }
}
