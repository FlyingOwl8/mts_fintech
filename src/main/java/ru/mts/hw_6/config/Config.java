package ru.mts.hw_6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw_6.repository.AnimalsRepository;
import ru.mts.hw_6.repository.impl.AnimalsRepositoryImpl;
import ru.mts.hw_6.service.CreateAnimalService;
import ru.mts.hw_6.service.impl.CreateAnimalServiceImpl;

@Configuration
public class Config {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessor() {
        return new CreateAnimalBeanPostProcessor();
    }

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        return createAnimalService;
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
