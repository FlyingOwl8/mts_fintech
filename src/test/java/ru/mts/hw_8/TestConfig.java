package ru.mts.hw_8;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mts.hw_8.service.impl.CreateAnimalServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public CreateAnimalServiceImpl createAnimalServiceTest() {
        CreateAnimalServiceImpl mock = Mockito.mock(CreateAnimalServiceImpl.class);
        return mock;
    }
}
