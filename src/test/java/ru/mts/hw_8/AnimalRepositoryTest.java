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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Animal> wolfList = List.of(
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10))
        );
        List<Animal> sharkList = List.of(
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10))
        );
        List<Animal> deerList = List.of(
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10))
        );
        List<Animal> rabbitList = List.of(
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(2024, 1, 10))
        );
        Map<String, List<Animal>> leapYearAnimals = Map.of(
                "gray wolf", wolfList,
                "tiger shark", sharkList,
                "european deer", deerList,
                "white rabbit", rabbitList
        );
        Mockito.when(createService.createAnimals()).thenReturn(leapYearAnimals);
        animalsRepository.postConstruct();

        Map<String, LocalDate> expectedAnimals = Map.of(
                "gray wolf wolf1", LocalDate.of(2024, 1, 10),
                "tiger shark shark1", LocalDate.of(2024, 1, 10),
                "european deer deer1", LocalDate.of(2024, 1, 10),
                "white rabbit rabbit1", LocalDate.of(2024, 1, 10)
        );

        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск несуществующих животных, родившихся в високосный год")
    public void findLeapYearNamesTest2() {
        List<Animal> wolfList = List.of(
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31))
        );
        List<Animal> sharkList = List.of(
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31))
        );
        List<Animal> deerList = List.of(
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31))
        );
        List<Animal> rabbitList = List.of(
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31))
        );
        Map<String, List<Animal>> leapYearAnimals = Map.of(
                "gray wolf", wolfList,
                "tiger shark", sharkList,
                "european deer", deerList,
                "white rabbit", rabbitList
        );
        Mockito.when(createService.createAnimals()).thenReturn(leapYearAnimals);
        animalsRepository.postConstruct();

        Map<String, LocalDate> expectedAnimals = new HashMap<>();

        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в пустом массиве")
    public void findLeapYearNamesTest3() {
        Map<String, List<Animal>> emptyAnimalsMap = new HashMap<>();
        Mockito.when(createService.createAnimals()).thenReturn(emptyAnimalsMap);
        animalsRepository.postConstruct();

        Map<String, LocalDate> expectedEmptyAnimals = new HashMap<>();
        Assertions.assertEquals(expectedEmptyAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в незаданном массиве")
    public void findLeapYearNamesTest4() {
        Map<String, List<Animal>> nullAnimalsMap = null;
        Mockito.when(createService.createAnimals()).thenReturn(nullAnimalsMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findLeapYearNames(), "Map of Animals is null");
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, в массиве с незаданными элементами")
    public void findLeapYearNamesTest5() {
        List<Animal> wolfList = new ArrayList<>();
        wolfList.add(null);
        List<Animal> deerList = new ArrayList<>();
        deerList.add(null);
        Map<String, List<Animal>> nullElementsMap = Map.of(
                "gray wolf", wolfList,
                "european deer", deerList
        );
        Mockito.when(createService.createAnimals()).thenReturn(nullElementsMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findLeapYearNames(), "Animal is null");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("Поиск животных старше данного возраста")
    public void findOlderAnimals1(int age) {
        Animal wolf = new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 4, 1, 1));
        Animal shark = new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 3, 1, 1));
        Animal deer = new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 2, 1, 1));
        Animal rabbit = new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.of(LocalDate.now().getYear() - 1, 1, 1));
        List<Animal> wolfList = List.of(wolf);
        List<Animal> sharkList = List.of(shark);
        List<Animal> deerList = List.of(deer);
        List<Animal> rabbitList = List.of(rabbit);
        Map<String, List<Animal>> animalMap = Map.of(
                "gray wolf", wolfList,
                "tiger shark", sharkList,
                "european deer", deerList,
                "white rabbit", rabbitList
        );
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Map<Animal, Integer> expectedAnimals = new HashMap<>();
        expectedAnimals.put(wolf, 4);
        if (age < 3) {
            expectedAnimals.put(shark, 3);
        }
        if (age < 2) {
            expectedAnimals.put(deer, 2);
        }
        if (age < 1) {
            expectedAnimals.put(rabbit, 1);
        }

        Assertions.assertEquals(expectedAnimals, animalsRepository.findOlderAnimals(age));
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в пустом массиве")
    public void findOlderAnimals2() {
        int age = 1;
        Map<String, List<Animal>> emptyMap = new HashMap<>();
        Mockito.when(createService.createAnimals()).thenReturn(emptyMap);
        animalsRepository.postConstruct();

        Map<Animal, Integer> expectedEmptyAnimals = new HashMap<>();

        Assertions.assertEquals(expectedEmptyAnimals, animalsRepository.findOlderAnimals(age));
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в незаданном массиве")
    public void findOlderAnimals3() {
        int age = 1;
        Map<String, List<Animal>> nullAnimalsMap = null;
        Mockito.when(createService.createAnimals()).thenReturn(nullAnimalsMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimals(age), "Map of Animals is null");
    }

    @Test
    @DisplayName("Поиск животных старше данного возраста в массиве с незаданными элементами")
    public void findOlderAnimals4() {
        int age = 1;
        List<Animal> wolfList = new ArrayList<>();
        wolfList.add(null);
        List<Animal> deerList = new ArrayList<>();
        deerList.add(null);
        Map<String, List<Animal>> nullElementsMap = Map.of(
                "gray wolf", wolfList,
                "european deer", deerList
        );
        Mockito.when(createService.createAnimals()).thenReturn(nullElementsMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimals(age), "Animal is null");
    }

    @Test
    @DisplayName("Поиск дубликатов животных в массиве с уникальными элементами")
    public void findDuplicatesTest1() {
        List<Animal> wolfList = List.of(
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now())
        );
        List<Animal> sharkList = List.of(
                new Shark("shark1", "tiger shark", BigDecimal.valueOf(15), LocalDate.now())
        );
        List<Animal> deerList = List.of(
                new Deer("deer1", "european deer", BigDecimal.valueOf(15), LocalDate.now())
        );
        List<Animal> rabbitList = List.of(
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now())
        );
        Map<String, List<Animal>> animalMap = Map.of(
                "gray wolf", wolfList,
                "tiger shark", sharkList,
                "european deer", deerList,
                "white rabbit", rabbitList
        );
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Map<String, Integer> expectedAnimals = new HashMap<>();

        Assertions.assertEquals(expectedAnimals, animalsRepository.findDuplicates());
    }


    @Test
    @DisplayName("Поиск дубликатов животных в массиве с повторяющимися элементами")
    public void findDuplicatesTest2() {
        List<Animal> wolfList = List.of(
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15), LocalDate.now())
        );
        List<Animal> rabbitList = List.of(
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("rabbit1", "white rabbit", BigDecimal.valueOf(15), LocalDate.now())
        );
        Map<String, List<Animal>> animalMap = Map.of(
                "gray wolf", wolfList,
                "white rabbit", rabbitList
        );
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Map<String, Integer> expectedAnimals = Map.of(
                "gray wolf", 2,
                "white rabbit", 1
        );

        Assertions.assertEquals(expectedAnimals, animalsRepository.findDuplicates());
    }

    @Test
    @DisplayName("Поиск дубликатов животных в пустом массиве")
    public void findDuplicatesTest3() {
        Map<String, List<Animal>> animalMap = new HashMap<>();
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Map<String, Integer> expectedAnimals = new HashMap<>();

        Assertions.assertEquals(expectedAnimals, animalsRepository.findDuplicates());
    }

    @Test
    @DisplayName("Поиск дубликатов животных в незаданном массиве")
    public void findDuplicatesTest4() {
        Map<String, List<Animal>> animalMap = null;
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findDuplicates(), "Map of Animals is null");
    }

    @Test
    @DisplayName("Тестирование поиска дубликатов животных в массиве с незаданными элементами")
    public void findDuplicatesTest5() {
        List<Animal> wolfList = new ArrayList<>();
        wolfList.add(null);
        List<Animal> deerList = new ArrayList<>();
        deerList.add(null);
        Map<String, List<Animal>> animalMap = Map.of(
                "gray wolf", wolfList,
                "european deer", deerList
        );
        Mockito.when(createService.createAnimals()).thenReturn(animalMap);
        animalsRepository.postConstruct();

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findDuplicates(), "Animal is null");
    }
}
