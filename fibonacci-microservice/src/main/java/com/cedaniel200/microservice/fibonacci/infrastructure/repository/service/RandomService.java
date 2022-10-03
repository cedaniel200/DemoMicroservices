package com.cedaniel200.microservice.fibonacci.infrastructure.repository.service;

import com.cedaniel200.microservice.fibonacci.domain.model.ValueRandom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomService {
    @GET("/random")
    Call<ValueRandom> getRandomNumber(@Query("bound")int bound);
}
