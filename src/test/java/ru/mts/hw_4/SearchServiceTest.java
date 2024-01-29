package ru.mts.hw_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Deer;
import ru.mts.hw_4.animal.impl.Rabbit;
import ru.mts.hw_4.animal.impl.Shark;
import ru.mts.hw_4.animal.impl.Wolf;
import ru.mts.hw_4.service.SearchService;
import ru.mts.hw_4.service.impl.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchServiceTest {
    SearchService searchService = new SearchServiceImpl();

    @Test
    @DisplayName("Тестирование поиска животных, родившихся в високосный год")
    public void findLeapYearNamesTest() {
        Animal[] leapYearAnimals = {
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Shark("tiger", BigDecimal.valueOf(15), LocalDate.now()),
                new Deer("european", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now()),
        };
        String[] actualLeapYearAnimals = {"wolf", "shark", "deer", "rabbit"};
        Animal[] noLeapYearAnimals = {
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Shark("tiger", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Deer("european", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
        };
        String[] actualEmptyAnimalsArray = {};
        Animal[] emptyAnimalsArray = {};
        Animal[] nullAnimalsArray = null;
        Animal[] nullElementsArray = {null, null};
        Assertions.assertArrayEquals(actualLeapYearAnimals, searchService.findLeapYearNames(leapYearAnimals));
        Assertions.assertArrayEquals(actualEmptyAnimalsArray, searchService.findLeapYearNames(noLeapYearAnimals));
        Assertions.assertArrayEquals(actualEmptyAnimalsArray, searchService.findLeapYearNames(emptyAnimalsArray));
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findLeapYearNames(nullAnimalsArray), "Array of Animals is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findLeapYearNames(nullElementsArray), "Animal is null");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("Тестирование поиска животных старше данного возраста")
    public void findOlderAnimals(int age) {
        Animal[] animalArray = {
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.of(2020, 1, 1)),
                new Shark("tiger", BigDecimal.valueOf(15), LocalDate.of(2021, 1, 1)),
                new Deer("european", BigDecimal.valueOf(15), LocalDate.of(2022, 1, 1)),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.of(2023, 1, 1)),
        };
        Animal[] correctAnswer = Arrays.copyOfRange(animalArray, 0, 4 - age);
        Animal[] emptyArray = {};
        Animal[] nullArray = null;
        Animal[] nullElementsArray = {null, null};
        Assertions.assertArrayEquals(correctAnswer, searchService.findOlderAnimals(animalArray, age));
        Assertions.assertArrayEquals(emptyArray, searchService.findOlderAnimals(emptyArray, age));
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findOlderAnimals(nullArray, age), "Array of Animals is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findOlderAnimals(nullElementsArray, age), "Animal is null");
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных")
    public void findDuplicatesTest() {
        Animal[] arrayOfUniqueArray = {
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Shark("tiger", BigDecimal.valueOf(15), LocalDate.now()),
                new Deer("european", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now()),
        };
        Animal[] arrayOfDuplicatedAnimals = {new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now())
        };
        List<Animal> emptyList = new ArrayList<>();
        Animal[] emptyArray = {};
        Animal[] nullArray = null;
        Animal[] nullElementArray = {null, null};
        List<Animal> answerForDuplicatedAnimals = new ArrayList<>() {{
            add(new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()));
            add(new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()));
            add(new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now()));
        }};
        Assertions.assertEquals(answerForDuplicatedAnimals, searchService.findDuplicates(arrayOfDuplicatedAnimals));
        Assertions.assertEquals(emptyList, searchService.findDuplicates(arrayOfUniqueArray));
        Assertions.assertEquals(emptyList, searchService.findDuplicates(emptyArray));
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findDuplicates(nullArray), "Array of Animals is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> searchService.findDuplicates(nullElementArray), "Animal is null");
    }
}
