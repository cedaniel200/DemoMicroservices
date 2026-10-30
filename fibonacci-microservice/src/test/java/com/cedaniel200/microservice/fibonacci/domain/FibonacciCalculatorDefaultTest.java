package com.cedaniel200.microservice.fibonacci.domain;

import com.cedaniel200.microservice.fibonacci.application.FibonacciCalculatorDefault;
import com.cedaniel200.microservice.fibonacci.domain.model.Fibonacci;
import com.cedaniel200.microservice.fibonacci.domain.model.ValueRandom;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.domain.util.ValidatorDefault;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.FibonacciDefaultRepository;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FibonacciCalculatorDefaultTest {

    @Autowired
    private FibonacciRepository fibonacciRepository;

    @MockBean
    private Retrofit retrofit;

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowAnIOException() throws IOException {
        RandomService mockRandomService = Mockito.mock(RandomService.class);
        Call<ValueRandom> mockCall = Mockito.mock(Call.class);
        when(retrofit.create(RandomService.class)).thenReturn(mockRandomService);
        when(mockRandomService.getRandomNumber(10)).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new IOException("Fallo de conexión simulado"));

        FibonacciRepository repository = new FibonacciDefaultRepository(retrofit);
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculatorDefault(repository, new ValidatorDefault());

        RuntimeException excepcion = assertThrows(
                RuntimeException.class,
                () -> fibonacciCalculator.calculateFibonacciOfARandomNumber(),
                "calculateFibonacciOfARandomNumber was expected to throw a RuntimeException, but it did not."
        );

        assertEquals("Error consuming random service", excepcion.getMessage());
    }

    @Test
    void shouldTActivateCircuitBreakerAndReturnRandomNumberWhenRetrofitFails() throws IOException {
        RandomService mockRandomService = Mockito.mock(RandomService.class);
        Call<ValueRandom> mockCall = Mockito.mock(Call.class);

        when(retrofit.create(RandomService.class)).thenReturn(mockRandomService);
        when(mockRandomService.getRandomNumber(10)).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new IOException("Fallo de conexión simulado"));

        FibonacciCalculator fibonacciCalculator = new FibonacciCalculatorDefault(fibonacciRepository, new ValidatorDefault());
        Fibonacci result = fibonacciCalculator.calculateFibonacciOfARandomNumber();

        assertNotNull(result, "The circuit breaker did not work.");
        int number = result.getNumber();

        assertTrue(number >= 0 && number < 10,"The circuit breaker did not work.");
    }
}
