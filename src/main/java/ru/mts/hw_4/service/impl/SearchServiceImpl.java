package ru.mts.hw_4.service.impl;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.service.SearchService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchServiceImpl implements SearchService {
    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     * @return Массив объектов класса String - имён животных
     */
    public String[] findLeapYearNames(Animal[] animalArray) {
        validateAnimals(animalArray);

        List<String> leapYearNamesArrayList = new ArrayList<>();

        for (Animal animal : animalArray) {
            if (animal.getBirthDate().isLeapYear()) {
                leapYearNamesArrayList.add(animal.getName());
            }
        }
        return leapYearNamesArrayList.toArray(new String[0]);
    }

    /**
     * Метод для нахождения в массиве животных тех животных,
     * которые старше N лет
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    public Animal[] findOlderAnimals(Animal[] animalArray, int n) {
        validateAnimals(animalArray);

        LocalDate currentDate = LocalDate.now();
        List<Animal> olderAnimalsArrayList = new ArrayList<>();

        for (Animal animal : animalArray) {
            if (Period.between(animal.getBirthDate(), currentDate).getYears() > n) {
                olderAnimalsArrayList.add(animal);
            }
        }
        return olderAnimalsArrayList.toArray(new Animal[0]);
    }

    /**
     * Метод для нахождения дубликатов в массиве животных
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     */
    public void findDuplicates(Animal[] animalArray) {
        validateAnimals(animalArray);

        Set<Animal> uniqueAnimals = new HashSet<>();

        for (Animal currentAnimal : animalArray) {
            if (uniqueAnimals.contains(currentAnimal)) {
                System.out.println("Найден дубликат: " + currentAnimal);
            }
            uniqueAnimals.add(currentAnimal);
        }
        System.out.println("-----");
    }

    private void validateAnimals(Animal[] animalsArray) {
        if (animalsArray == null) {
            throw new IllegalArgumentException("Array of Animals is null");
        }
        for (Animal animal : animalsArray) {
            if (animal == null) {
                throw new IllegalArgumentException("Animal is null");
            }
        }
    }
}
