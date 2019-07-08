package com.example.microservices.add.config;

import com.example.microservices.add.domain.Adder;
import com.example.microservices.add.domain.AdderDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    @Bean
    public Adder providesAdderDefaultInstance() {
        return new AdderDefault();
    }
}
