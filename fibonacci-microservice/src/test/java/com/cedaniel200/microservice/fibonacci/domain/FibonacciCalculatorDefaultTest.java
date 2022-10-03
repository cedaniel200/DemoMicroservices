package com.cedaniel200.microservice.fibonacci.domain;

import com.cedaniel200.microservice.fibonacci.domain.model.Fibonacci;
import com.cedaniel200.microservice.fibonacci.domain.model.ValueRandom;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.FibonacciDefaultRepository;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.service.RandomService;
import com.cedaniel200.microservice.fibonacci.domain.util.ValidatorDefault;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

class FibonacciCalculatorDefaultTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowAnIOException() throws IOException {
        Call<ValueRandom> call = Mockito.mock(Call.class);
        Mockito.when(call.execute()).thenThrow(new IOException());
        RandomService randomService = Mockito.mock(RandomService.class);
        Mockito.when(randomService.getRandomNumber(any(Integer.class))).thenReturn(call);
        Retrofit retrofit = Mockito.mock(Retrofit.class);
        Mockito.when(retrofit.create(RandomService.class)).thenReturn(randomService);
        FibonacciRepository repository = new FibonacciDefaultRepository(retrofit);
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculatorDefault(repository, new ValidatorDefault());
        Fibonacci expectedResponse = new Fibonacci(-1, -1);

        Fibonacci fibonacci = fibonacciCalculator.calculateFibonacciOfARandomNumber();

        assertThat(fibonacci.getResult(), is(expectedResponse.getResult()));
    }
}
