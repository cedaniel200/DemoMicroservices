package com.example.microservice.subtract.config;

import com.example.microservice.subtract.domain.Subtractor;
import com.example.microservice.subtract.domain.SubtractorDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public Subtractor providesSubtractorDefaultInstance() {
        return new SubtractorDefault();
    }
}
