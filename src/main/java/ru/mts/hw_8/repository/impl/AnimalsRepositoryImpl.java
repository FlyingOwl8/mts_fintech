package ru.mts.hw_8.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.repository.AnimalsRepository;
import ru.mts.hw_8.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Component
@RequiredArgsConstructor
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private Map<String, List<Animal>> animalsMap;
    private final CreateAnimalService createAnimalService;

    @PostConstruct
    public void postConstruct() {
        animalsMap = createAnimalService.createAnimals();
    }


    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @return Массив объектов класса String - имён животных
     */
    public Map<String, LocalDate> findLeapYearNames() {
        validateAnimals();

        Map<String, LocalDate> leapYearNames = new HashMap<>();

        for (String animalType : animalsMap.keySet()) {
            for (Animal animal : animalsMap.get(animalType)) {
                if (animal.getBirthDate().isLeapYear()) {
                    leapYearNames.put(animalType.concat(" ".concat(animal.getName())), animal.getBirthDate());
                }
            }
        }
        return leapYearNames;
    }

    /**
     * Метод для нахождения в массиве животных тех животных,
     * которые старше N лет
     *
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    public Map<Animal, Integer> findOlderAnimals(int n) {
        validateAnimals();

        LocalDate currentDate = LocalDate.now();
        Map<Animal, Integer> olderAnimalsMap = new HashMap<>();

        Animal oldestAnimal = null;

        for (String animalType : animalsMap.keySet()) {
            for (Animal animal : animalsMap.get(animalType)) {
                if (Period.between(animal.getBirthDate(), currentDate).getYears() > n) {
                    olderAnimalsMap.put(animal, Period.between(animal.getBirthDate(), currentDate).getYears());
                }
                if (oldestAnimal == null) {
                    oldestAnimal = animal;
                } else if (Period.between(animal.getBirthDate(), currentDate).getYears() >
                        Period.between(oldestAnimal.getBirthDate(), currentDate).getYears()) {
                    oldestAnimal = animal;
                }
            }
        }
        if (olderAnimalsMap.isEmpty()) {
            if (oldestAnimal != null) {
                olderAnimalsMap.put(oldestAnimal, Period.between(oldestAnimal.getBirthDate(), currentDate).getYears());
            }
        }
        return olderAnimalsMap;
    }

    /**
     * Метод для нахождения дубликатов в массиве животных
     *
     */
    public Map<String, Integer> findDuplicates() {
        validateAnimals();

        Set<Animal> uniqueAnimals = new HashSet<>();
        Map<String, Integer> duplicatedAnimals = new HashMap<>();

        for (String animalType : animalsMap.keySet()) {
            for (Animal currentAnimal : animalsMap.get(animalType)) {
                if (uniqueAnimals.contains(currentAnimal)) {
                    if (duplicatedAnimals.containsKey(currentAnimal.getBreed())) {
                        int count = duplicatedAnimals.get(currentAnimal.getBreed());
                        duplicatedAnimals.put(currentAnimal.getBreed(), count + 1);
                    } else {
                        duplicatedAnimals.put(currentAnimal.getBreed(), 1);
                    }
                }
                uniqueAnimals.add(currentAnimal);
            }
        }
        return duplicatedAnimals;
    }

    public void printDuplicates() {
        Map<String, Integer> duplicatedAnimals = findDuplicates();
        for (String currentAnimal : duplicatedAnimals.keySet()) {
            System.out.println("Найден дубликат: " + currentAnimal);
        }
        System.out.println("-----");
    }

    private void validateAnimals() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Map of Animals is null");
        }
        for (String animalType : animalsMap.keySet()) {
            for (Animal animal : animalsMap.get(animalType)) {
                if (animal == null) {
                    throw new IllegalArgumentException("Animal is null");
                }
            }
        }
    }
}
