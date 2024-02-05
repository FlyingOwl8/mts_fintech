package ru.mts.hw_6.repository.impl;

import ru.mts.hw_6.animal.Animal;
import ru.mts.hw_6.repository.AnimalsRepository;
import ru.mts.hw_6.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AnimalsRepositoryImpl implements AnimalsRepository {
    Animal[] animalArray;
    CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    private void postConstruct() {
        animalArray = createAnimalService.createAnimals();
    }


    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @return Массив объектов класса String - имён животных
     */
    public String[] findLeapYearNames() {
        validateAnimals();

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
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    public Animal[] findOlderAnimals(int n) {
        validateAnimals();

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
     */
    public List<Animal> findDuplicates() {
        validateAnimals();

        Set<Animal> uniqueAnimals = new HashSet<>();
        List<Animal> duplicatedAnimals = new ArrayList<>();
        for (Animal currentAnimal : animalArray) {
            if (uniqueAnimals.contains(currentAnimal)) {
                duplicatedAnimals.add(currentAnimal);
            }
            uniqueAnimals.add(currentAnimal);
        }
        return duplicatedAnimals;
    }

    public void printDuplicates() {
        List<Animal> duplicatedAnimals = findDuplicates();
        for (Animal currentAnimal : duplicatedAnimals) {
            System.out.println("Найден дубликат: " + currentAnimal);
        }
        System.out.println("-----");
    }

    private void validateAnimals() {
        if (animalArray == null) {
            throw new IllegalArgumentException("Array of Animals is null");
        }
        for (Animal animal : animalArray) {
            if (animal == null) {
                throw new IllegalArgumentException("Animal is null");
            }
        }
    }
}
