package com.cedaniel200.microservice.fibonacci.config;

import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculator;
import com.cedaniel200.microservice.fibonacci.util.Validator;
import com.cedaniel200.microservice.fibonacci.util.ValidatorDefault;
import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculatorDefault;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class DomainConfiguration {

    @Bean
    public Retrofit providesRetrofitInstance(@Value("${random-number.url}") String randomNumberUrl) {
        return new Retrofit.Builder()
                .baseUrl(randomNumberUrl)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .create()))
                .build();
    }

    @Bean
    public Validator providesValidatorDefaultInstance() {
        return new ValidatorDefault();
    }

    @Bean
    public FibonacciCalculator providesFibonacciCalculatorDefaultInstance(Retrofit retrofit, Validator validator) {
        return new FibonacciCalculatorDefault(retrofit, validator);
    }
}
