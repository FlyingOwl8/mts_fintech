package ru.mts.hw_7.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalNameConfig {
    @Value("${animal.wolf.names}")
    private String[] arrayOfStrings;

    public String[] getArrayOfStrings() {
        return arrayOfStrings;
    }
}
