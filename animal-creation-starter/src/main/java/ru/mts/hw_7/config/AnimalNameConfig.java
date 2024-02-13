package ru.mts.hw_7.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class AnimalNameConfig {
    @Value("${animal.wolf.names}")
    private String[] wolfNames;
    @Value("${animal.shark.names}")
    private String[] sharkNames;
    @Value("${animal.rabbit.names}")
    private String[] rabbitNames;
    @Value("${animal.deer.names}")
    private String[] deerNames;

}
