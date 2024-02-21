package ru.mts.hw_8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.factory.AnimalTypes;
import ru.mts.hw_8.factory.impl.AnimalFactoryImpl;
import ru.mts.hw_8.factory.impl.RabbitFactory;
import ru.mts.hw_8.factory.impl.WolfFactory;
import ru.mts.hw_8.service.impl.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class CreateAnimalServiceTest {
    @Autowired
    private CreateAnimalServiceImpl createService;
    @Autowired
    private WolfFactory wolfFactory;
    @Autowired
    private RabbitFactory rabbitFactory;
    @MockBean
    private AnimalFactoryImpl factory;

    @Test
    @DisplayName("Тестирование сервиса создания животных")
    public void createAnimalsTest() {
        Mockito.when(factory.createAnimal(AnimalTypes.WOLF, "gray wolf", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 1))).thenReturn(wolfFactory.createAnimal("new_wolf", "gray wolf", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 1)));
        Mockito.when(factory.createAnimal(AnimalTypes.RABBIT, "white rabbit", BigDecimal.valueOf(0.75), LocalDate.ofYearDay(2020, 3))).thenReturn(rabbitFactory.createAnimal("new_rabbit", "white rabbit", BigDecimal.valueOf(0.75), LocalDate.ofYearDay(2020, 3)));

        Animal[] expectedArray = {
                wolfFactory.createAnimal("new_wolf", "gray wolf", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 1)),
                rabbitFactory.createAnimal("new_rabbit", "white rabbit", BigDecimal.valueOf(0.75), LocalDate.ofYearDay(2020, 3)),
        };
        Animal[] actualArray = createService.createAnimals(2);

        Assertions.assertArrayEquals(expectedArray, actualArray);
    }
}
