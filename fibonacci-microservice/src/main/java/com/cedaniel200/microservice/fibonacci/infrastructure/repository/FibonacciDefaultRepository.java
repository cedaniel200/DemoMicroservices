package com.cedaniel200.microservice.fibonacci.infrastructure.repository;

import com.cedaniel200.microservice.fibonacci.domain.model.ValueRandom;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.service.RandomService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.Random;
import java.util.logging.Logger;

import static java.lang.String.format;

@Repository
@Primary
public class FibonacciDefaultRepository implements FibonacciRepository {

    private final RandomService service;
    private static final Logger LOG = Logger.getLogger(FibonacciDefaultRepository.class.getName());

    public FibonacciDefaultRepository(Retrofit retrofit) {
        this.service = retrofit.create(RandomService.class);
    }


    @CircuitBreaker(name = "randomService", fallbackMethod = "fallbackGetRandomNumber")
    @Override
    public int getRandomNumber() {
        try {
            Call<ValueRandom> call = service.getRandomNumber(10);
            Response<ValueRandom> response = call.execute();
            return response.body().getValue();
        } catch (Exception e) {
            LOG.warning(format("Error consuming random service: %s", e.getMessage()));
            throw new RuntimeException("Error consuming random service");
        }
    }

    private int fallbackGetRandomNumber(RuntimeException e) {
        LOG.warning(format("Servicio degradado por excepcion: %s", e.getMessage()));
        return new Random().nextInt(10);
    }
}
