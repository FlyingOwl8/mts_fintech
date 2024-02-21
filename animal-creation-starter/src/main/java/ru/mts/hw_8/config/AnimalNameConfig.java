package ru.mts.hw_8.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "animal")
public class AnimalNameConfig {
    private List<String> wolfNames;
    private List<String> sharkNames;
    private List<String> rabbitNames;
    private List<String> deerNames;
}
