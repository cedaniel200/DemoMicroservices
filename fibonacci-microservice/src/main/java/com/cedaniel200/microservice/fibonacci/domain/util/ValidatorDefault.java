package com.cedaniel200.microservice.fibonacci.domain.util;

public class ValidatorDefault implements Validator {
    @Override
    public boolean isNumberValid(int number){
        return number >= 0 && number <= 10;
    }
}
