package ru.mts.hw_7.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Config {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessor() {
        return new CreateAnimalBeanPostProcessor();
    }
/**
    @Bean
    @Scope("prototype") public CreateAnimalService createAnimalService(AnimalFactory factory) {
    CreateAnimalService createAnimalService = new CreateAnimalServiceImpl(factory);
        return createAnimalService;
    }
 */
}
