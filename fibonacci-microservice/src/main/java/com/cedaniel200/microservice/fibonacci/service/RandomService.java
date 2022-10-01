package com.cedaniel200.microservice.fibonacci.service;

import com.cedaniel200.microservice.fibonacci.model.ValueRandom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomService {
    @GET("/random")
    Call<ValueRandom> getRandomNumer(@Query("bound")int bound);
}
