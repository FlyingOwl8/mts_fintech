package ru.mts.hw;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mts.hw.service.impl.CreateAnimalServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public CreateAnimalServiceImpl createAnimalServiceTest() {
        CreateAnimalServiceImpl mock = Mockito.mock(CreateAnimalServiceImpl.class);
        return mock;
    }
}
