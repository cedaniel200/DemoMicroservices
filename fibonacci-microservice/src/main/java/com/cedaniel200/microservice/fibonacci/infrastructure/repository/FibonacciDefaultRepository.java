package com.cedaniel200.microservice.fibonacci.infrastructure.repository;

import com.cedaniel200.microservice.fibonacci.domain.model.ValueRandom;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.service.RandomService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.logging.Logger;

import static java.lang.String.format;

public class FibonacciDefaultRepository implements FibonacciRepository {

    private final RandomService service;
    private final Logger log = Logger.getLogger(FibonacciDefaultRepository.class.getName());

    public FibonacciDefaultRepository(Retrofit retrofit) {
        this.service = retrofit.create(RandomService.class);
    }

    @Override
    public int getRandomNumber() {
        try {
            Call<ValueRandom> call = service.getRandomNumber(10);
            Response<ValueRandom> response = call.execute();
            return response.body().getValue();
        } catch (Exception e) {
            log.warning(format("Error consuming random service: %s", e.getMessage()));
            return -1;
        }
    }
}
