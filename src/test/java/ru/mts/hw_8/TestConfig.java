package ru.mts.hw_8;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.hw_8.service.impl.CreateAnimalServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    public CreateAnimalServiceImpl createAnimalServiceTest() {
        CreateAnimalServiceImpl mock = Mockito.mock(CreateAnimalServiceImpl.class);
        return mock;
    }
}
