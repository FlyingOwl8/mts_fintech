package ru.mts.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.factory.AnimalTypes;
import ru.mts.hw.factory.impl.AnimalFactoryImpl;
import ru.mts.hw.factory.impl.RabbitFactory;
import ru.mts.hw.factory.impl.WolfFactory;
import ru.mts.hw.service.impl.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, List<Animal>> expectedMap = new HashMap<>();
        List<Animal> wolfList = new ArrayList<>();
        wolfList.add(wolfFactory.createAnimal("new_wolf", "gray wolf", BigDecimal.valueOf(15), LocalDate.ofYearDay(2020, 1)));
        expectedMap.put("gray wolf", wolfList);
        List<Animal> rabbitList = new ArrayList<>();
        rabbitList.add(rabbitFactory.createAnimal("new_rabbit", "white rabbit", BigDecimal.valueOf(0.75), LocalDate.ofYearDay(2020, 3)));
        expectedMap.put("white rabbit", rabbitList);

        Map<String, List<Animal>> actualMap = createService.createAnimals(2);

        Assertions.assertEquals(expectedMap, actualMap);
    }
}
