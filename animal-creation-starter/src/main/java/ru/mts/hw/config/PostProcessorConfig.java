package ru.mts.hw.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class PostProcessorConfig {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessor() {
        return new CreateAnimalBeanPostProcessor();
    }
}
