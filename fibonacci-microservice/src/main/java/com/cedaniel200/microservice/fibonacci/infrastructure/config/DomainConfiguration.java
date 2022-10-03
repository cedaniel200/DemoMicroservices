package com.cedaniel200.microservice.fibonacci.infrastructure.config;

import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculator;
import com.cedaniel200.microservice.fibonacci.domain.FibonacciCalculatorDefault;
import com.cedaniel200.microservice.fibonacci.domain.repository.FibonacciRepository;
import com.cedaniel200.microservice.fibonacci.domain.util.Validator;
import com.cedaniel200.microservice.fibonacci.domain.util.ValidatorDefault;
import com.cedaniel200.microservice.fibonacci.infrastructure.repository.FibonacciDefaultRepository;
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
    public FibonacciRepository providesFibonacciDefaultRepositoryInstance(Retrofit retrofit) {
        return new FibonacciDefaultRepository(retrofit);
    }

    @Bean
    public FibonacciCalculator providesFibonacciCalculatorDefaultInstance(FibonacciRepository fibonacciRepository, Validator validator) {
        return new FibonacciCalculatorDefault(fibonacciRepository, validator);
    }
}
