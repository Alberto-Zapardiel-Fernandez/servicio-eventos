package com.application.config;

import com.domain.ports.out.EventRepositoryPort;
import com.domain.usecases.EventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EventUseCase eventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new EventUseCase(eventRepositoryPort);
    }
}
