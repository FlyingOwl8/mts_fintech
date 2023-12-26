package ru.mts;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.service.SearchService;
import ru.mts.hw_4.service.impl.CreateAnimalServiceImpl;
import ru.mts.hw_4.service.impl.SearchServiceImpl;


public class Main {
    public static void main(String[] args) {
        Animal[] animalArray;
        CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();
        String[] animalNamesArray;
        Animal[] olderAnimalsArray;

        animalArray = createService.createAnimals();
        animalNamesArray = searchService.findLeapYearNames(animalArray);
        System.out.println("Животные, родившиеся в високосный год:");
        for (String animalName : animalNamesArray) {
            System.out.println(animalName);
        }
        System.out.println("-----");
        olderAnimalsArray = searchService.findOlderAnimals(animalArray, 1);
        System.out.println("Животные старше 1 года:");
        for (Animal animal : olderAnimalsArray) {
            System.out.println(animal);
        }
        System.out.println("-----");
        System.out.println("Животные, встречающиеся несколько раз:");
        searchService.findDuplicates(animalArray);

        animalArray = createService.createAnimals(5);
        animalNamesArray = searchService.findLeapYearNames(animalArray);
        System.out.println("Животные, родившиеся в високосный год:");
        for (String animalName : animalNamesArray) {
            System.out.println(animalName);
        }
        System.out.println("-----");
        olderAnimalsArray = searchService.findOlderAnimals(animalArray, 1);
        System.out.println("Животные старше 1 года:");
        for (Animal animal : olderAnimalsArray) {
            System.out.println(animal);
        }
        System.out.println("-----");
        System.out.println("Животные, встречающиеся несколько раз:");
        searchService.findDuplicates(animalArray);
    }
}