package ru.mts;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Rabbit;
import ru.mts.hw_4.animal.impl.Wolf;
import ru.mts.hw_4.service.SearchService;
import ru.mts.hw_4.service.impl.CreateAnimalServiceImpl;
import ru.mts.hw_4.service.impl.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();
        Animal[] arrayOfDuplicatedAnimals = {new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Wolf("gray", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now()),
                new Rabbit("white", BigDecimal.valueOf(15), LocalDate.now())
        };

        Animal[] animalArray = createService.createAnimals(5);
        String[] animalNamesArray = searchService.findLeapYearNames(animalArray);
        System.out.println("Животные, родившиеся в високосный год:");
        for (String animalName : animalNamesArray) {
            System.out.println(animalName);
        }
        System.out.println("-----");
        Animal[] olderAnimalsArray = searchService.findOlderAnimals(animalArray, 1);
        System.out.println("Животные старше 1 года:");
        for (Animal animal : olderAnimalsArray) {
            System.out.println(animal);
        }
        System.out.println("-----");
        System.out.println("Поиск одинаковых животные в массиве с разными животными:");
        searchService.findDuplicates(animalArray);

        System.out.println("Поиск одинаковых животных в массиве с дубликатами:");
        searchService.findDuplicates(arrayOfDuplicatedAnimals);
    }
}