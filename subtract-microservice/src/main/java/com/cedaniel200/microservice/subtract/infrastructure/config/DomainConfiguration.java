package com.cedaniel200.microservice.subtract.infrastructure.config;

import com.cedaniel200.microservice.subtract.domain.Subtractor;
import com.cedaniel200.microservice.subtract.domain.SubtractorDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public Subtractor providesSubtractorDefaultInstance() {
        return new SubtractorDefault();
    }
}
