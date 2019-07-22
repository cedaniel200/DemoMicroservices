package com.example.microservice.fibonacci.domain;

import com.example.microservice.fibonacci.model.Fibonacci;
import com.example.microservice.fibonacci.model.ValueRandom;
import com.example.microservice.fibonacci.service.RandomService;
import com.example.microservice.fibonacci.util.ValidatorDefault;
import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

public class FibonacciCalculatorDefaultTest {

    @Test
    @SuppressWarnings("unchecked")
    public void shouldThrowAnIOException() throws IOException {
        Call<ValueRandom> call = Mockito.mock(Call.class);
        Mockito.when(call.execute()).thenThrow(new IOException());
        RandomService randomService = Mockito.mock(RandomService.class);
        Mockito.when(randomService.getRandomNumer(any(Integer.class))).thenReturn(call);
        Retrofit retrofit = Mockito.mock(Retrofit.class);
        Mockito.when(retrofit.create(RandomService.class)).thenReturn(randomService);
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculatorDefault(retrofit, new ValidatorDefault());
        Fibonacci expectedResponse = new Fibonacci(-1, -1);

        Fibonacci fibonacci = fibonacciCalculator.calculateFibonacciOfARandomNumber();

        assertThat(fibonacci.getResult(), is(expectedResponse.getResult()));
    }
}
