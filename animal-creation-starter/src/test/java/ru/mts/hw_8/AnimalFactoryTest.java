package ru.mts.hw_8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.config.AnimalNameConfig;
import ru.mts.hw_8.factory.AnimalTypes;
import ru.mts.hw_8.factory.impl.AnimalFactoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest
public class AnimalFactoryTest {
    @Autowired
    private AnimalFactoryImpl factory;
    @Autowired
    private AnimalNameConfig animalNameConfig;

    @Test
    @DisplayName("Создание животных")
    public void createAnimalsTest() {
        Animal wolf = factory.createAnimal(AnimalTypes.WOLF, "gray wolf", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3));
        Assertions.assertTrue(animalNameConfig.getWolfNames().stream().anyMatch(name -> wolf.getName().equals(name)));

        Animal rabbit = factory.createAnimal(AnimalTypes.RABBIT, "white rabbit", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3));
        Assertions.assertTrue(animalNameConfig.getRabbitNames().stream().anyMatch(name -> rabbit.getName().equals(name)));

        Animal shark = factory.createAnimal(AnimalTypes.SHARK, "tiger shark", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3));
        Assertions.assertTrue(animalNameConfig.getSharkNames().stream().anyMatch(name -> shark.getName().equals(name)));

        Animal deer = factory.createAnimal(AnimalTypes.DEER, "european deer", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3));
        Assertions.assertTrue(animalNameConfig.getDeerNames().stream().anyMatch(name -> deer.getName().equals(name)));
    }

    @Test
    @DisplayName("Некорректное создание животных")
    public void createIncorrectAnimalsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, null, BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3)), "Field is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, "gray wolf", null, LocalDate.ofYearDay(2020, 3)), "Field is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, "gray wolf", BigDecimal.valueOf(15), null), "Field is null");

        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, "gray wolf", BigDecimal.valueOf(-15), LocalDate.ofYearDay(2020, 3)), "Cost can't be negative");

        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, "", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3)), "Breed can't be an empty string");
        Assertions.assertThrows(IllegalArgumentException.class, () -> factory.createAnimal(AnimalTypes.WOLF, "     ", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 3)), "Breed can't be an empty string");
    }
}
