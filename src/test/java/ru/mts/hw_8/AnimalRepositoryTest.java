package ru.mts.hw_8;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.animal.impl.Deer;
import ru.mts.hw_8.animal.impl.Rabbit;
import ru.mts.hw_8.animal.impl.Shark;
import ru.mts.hw_8.animal.impl.Wolf;
import ru.mts.hw_8.repository.impl.AnimalsRepositoryImpl;
import ru.mts.hw_8.service.impl.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AnimalsRepositoryImpl.class)
@Import(TestConfig.class)
public class AnimalRepositoryTest {
    @Autowired
    private CreateAnimalServiceImpl createService;
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Test
    @DisplayName("Поиск существующих животных, родившихся в високосный год")
    public void findLeapYearNamesTest1() {
        Animal[] leapYearAnimals = {
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10)),
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10)),
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10)),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10)),
        };
        Mockito.when(createService.createAnimals()).thenReturn(leapYearAnimals);
        animalsRepository.postConstruct();

        String[] expectedLeapYearAnimals = {"wolf1", "shark1", "deer1", "rabbit1"};
        Assertions.assertArrayEquals(expectedLeapYearAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск несуществующих животных, родившихся в високосный год")
    public void findLeapYearNamesTest2() {
        Animal[] noLeapYearAnimals = {
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31)),
        };
        Mockito.when(createService.createAnimals()).thenReturn(noLeapYearAnimals);
        animalsRepository.postConstruct();

        String[] EmptyAnimalsArray = {};
        Assertions.assertArrayEquals(EmptyAnimalsArray, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в пустом массиве")
    public void findLeapYearNamesTest3() {
        Animal[] emptyAnimalsArray = {};
        Mockito.when(createService.createAnimals()).thenReturn(emptyAnimalsArray);
        animalsRepository.postConstruct();

        String[] EmptyAnimalsArray = {};
        Assertions.assertArrayEquals(EmptyAnimalsArray, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в незаданном массиве")
    public void findLeapYearNamesTest4() {
        Animal[] nullAnimalsArray = null;
        Mockito.when(createService.createAnimals()).thenReturn(nullAnimalsArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findLeapYearNames(), "Array of Animals is null");
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в массиве с незаданными элементами")
    public void findLeapYearNamesTest5() {
        Animal[] nullElementsArray = {null, null};
        Mockito.when(createService.createAnimals()).thenReturn(nullElementsArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findLeapYearNames(), "Animal is null");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("Поиск животных старше данного возраста")
    public void findOlderAnimals1(int age) {
        Animal[] animalArray = {
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 4, 1, 1)),
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 3, 1, 1)),
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 2, 1, 1)),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 1, 1, 1)),
        };
        Mockito.when(createService.createAnimals()).thenReturn(animalArray);
        animalsRepository.postConstruct();

        Animal[] correctAnswer = Arrays.copyOfRange(animalArray, 0, 4 - age);
        Assertions.assertArrayEquals(correctAnswer, animalsRepository.findOlderAnimals(age));
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в пустом массиве")
    public void findOlderAnimals2() {
        int age = 1;
        Animal[] emptyArray = {};
        Mockito.when(createService.createAnimals()).thenReturn(emptyArray);
        animalsRepository.postConstruct();

        Assertions.assertArrayEquals(emptyArray, animalsRepository.findOlderAnimals(age));
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в незаданном массиве")
    public void findOlderAnimals3() {
        int age = 1;
        Animal[] nullArray = null;
        Mockito.when(createService.createAnimals()).thenReturn(nullArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimals(age), "Array of Animals is null");
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в массиве с незаданными элементами")
    public void findOlderAnimals4() {
        int age = 1;
        Animal[] nullElementsArray = {null, null};
        Mockito.when(createService.createAnimals()).thenReturn(nullElementsArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimals(age), "Animal is null");
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в массиве с уникальными элементами")
    public void findDuplicatesTest1() {
        Animal[] arrayOfUniqueArray = {
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.now()),
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now()),
        };
        Mockito.when(createService.createAnimals()).thenReturn(arrayOfUniqueArray);
        animalsRepository.postConstruct();

        List<Animal> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, animalsRepository.findDuplicates());
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в массиве с повторяющимися элементами")
    public void findDuplicatesTest2() {
        Animal[] arrayOfDuplicatedAnimals = {new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now())
        };
        Mockito.when(createService.createAnimals()).thenReturn(arrayOfDuplicatedAnimals);
        animalsRepository.postConstruct();

        List<Animal> answerForDuplicatedAnimals = new ArrayList<>() {{
            add(new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()));
            add(new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()));
            add(new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now()));
        }};
        Assertions.assertEquals(answerForDuplicatedAnimals, animalsRepository.findDuplicates());
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в пустом массиве")
    public void findDuplicatesTest3() {
        Animal[] emptyArray = {};
        Mockito.when(createService.createAnimals()).thenReturn(emptyArray);
        animalsRepository.postConstruct();

        List<Animal> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, animalsRepository.findDuplicates());
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в незаданном массиве")
    public void findDuplicatesTest4() {
        Animal[] nullArray = null;
        Mockito.when(createService.createAnimals()).thenReturn(nullArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findDuplicates(), "Array of Animals is null");
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в массиве с незаданными элементами")
    public void findDuplicatesTest5() {
        Animal[] nullElementArray = {null, null};
        Mockito.when(createService.createAnimals()).thenReturn(nullElementArray);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findDuplicates(), "Animal is null");
    }
}
