package ru.mts.hw_8.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Getter
@Configuration
public class AnimalNameConfig {
    @Value("${animal.wolf.names}")
    private List<String> wolfNames;
    @Value("${animal.shark.names}")
    private List<String> sharkNames;
    @Value("${animal.rabbit.names}")
    private List<String> rabbitNames;
    @Value("${animal.deer.names}")
    private List<String> deerNames;

}
