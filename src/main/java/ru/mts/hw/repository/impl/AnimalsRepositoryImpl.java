package ru.mts.hw.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.exception.MyCheckedException;
import ru.mts.hw.exception.MyUncheckedException;
import ru.mts.hw.repository.AnimalsRepository;
import ru.mts.hw.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Map<String, LocalDate> leapYearNames = new ConcurrentHashMap<>();

        animalsMap.entrySet()
                .stream()
                .forEach(pair -> pair.getValue()
                        .stream()
                        .filter(animal -> animal.getBirthDate().isLeapYear())
                        .forEach(rightAnimal -> leapYearNames.put(
                                        pair.getKey().concat(" ".concat(rightAnimal.getName())), rightAnimal.getBirthDate()
                                )
                        )
                );
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
        if (n < 0) {
            throw new MyUncheckedException("Age of animal can't be negative");
        }
        validateAnimals();

        LocalDate currentDate = LocalDate.now();
        Map<Animal, Integer> olderAnimalsMap = new ConcurrentHashMap<>();

        Animal oldestAnimal = null;

        animalsMap.entrySet()
                .stream()
                .forEach(pair -> pair.getValue()
                        .stream()
                        .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > n)
                        .forEach(rightAnimal -> olderAnimalsMap.put(
                                        rightAnimal, Period.between(rightAnimal.getBirthDate(), LocalDate.now()).getYears()
                                )
                        )
                );

        if (olderAnimalsMap.isEmpty()) {
            oldestAnimal = animalsMap.values()
                    .stream()
                    .flatMap(List::stream)
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .orElse(null);
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
    public Map<String, List<Animal>> findDuplicates() {
        validateAnimals();

        Map<Animal, Long> animalsFrequencies =
                animalsMap.values()
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, List<Animal>> duplicatedAnimals = new ConcurrentHashMap<>();
        animalsMap.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> animalsFrequencies.get(animal) > 1)
                .forEach(animal -> {
                            if (duplicatedAnimals.containsKey(animal.getBreed())) {
                                List<Animal> list = duplicatedAnimals.get(animal.getBreed());
                                list.add(animal);
                            } else {
                                List<Animal> list = new CopyOnWriteArrayList<>();
                                list.add(animal);
                                duplicatedAnimals.put(animal.getBreed(), list);
                            }
                        }
                );
        return duplicatedAnimals;
    }

    public String generateDuplicatesText() {
        Map<String, List<Animal>> duplicatedAnimals = findDuplicates();
        StringBuilder duplicatesString = new StringBuilder();
        for (String currentAnimalType : duplicatedAnimals.keySet()) {
            duplicatesString.append("Найдены дубликаты ").append(currentAnimalType).append(":\n");
            for (Animal animal : duplicatedAnimals.get(currentAnimalType)) {
                duplicatesString.append(animal).append("\n");
            }
            duplicatesString.append("---Конец дубликатов---\n");
        }
        duplicatesString.append("-----\n");
        return duplicatesString.toString();
    }

    public void printDuplicates() {
        System.out.println(generateDuplicatesText());
    }

    private void validateAnimals() {
        if (animalsMap == null) {
            throw new MyUncheckedException("Map of Animals is null");
        }
        for (String animalType : animalsMap.keySet()) {
            for (Animal animal : animalsMap.get(animalType)) {
                if (animal == null) {
                    throw new MyUncheckedException("Animal is null");
                }
            }
        }
    }

    public double countAverageAge(List<Animal> animalsList) {
        if (animalsList == null) {
            throw new MyUncheckedException("List of Animals is null");
        }
        for (Animal animal : animalsList) {
            if (animal == null) {
                throw new MyUncheckedException("Animal is null");
            }
        }

        double ans = animalsList
                .stream()
                .map(Animal::getBirthDate)
                .map(x -> Period.between(x, LocalDate.now()).getYears())
                .mapToInt(Integer::intValue)
                .average().orElse(0.0);
        return ans;
    }

    public void findAverageAge(List<Animal> animalsList) {
        System.out.println("Средний возраст: " + countAverageAge(animalsList));
    }

    public List<Animal> findOldAndExpensive(List<Animal> animalsList) {
        if (animalsList == null) {
            throw new MyUncheckedException("List of Animals is null");
        }
        for (Animal animal : animalsList) {
            if (animal == null) {
                throw new MyUncheckedException("Animal is null");
            }
        }

        BigDecimal sum = animalsList
                .stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageCost = sum.divide(BigDecimal.valueOf(animalsList.size()));

        List<Animal> sortedAnimals = new CopyOnWriteArrayList<>();
        animalsList.stream()
                .filter(animal -> Period.between(
                                animal.getBirthDate(), LocalDate.now()
                        ).getYears() > 5 &&
                                animal.getCost().compareTo(averageCost) > 0
                )
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .forEach(sortedAnimals::add);

        return sortedAnimals;
    }

    public List<Animal> findMinConstAnimals(List<Animal> animalsList) throws MyCheckedException {
        if (animalsList == null) {
            throw new MyUncheckedException("List of Animals is null");
        }
        for (Animal animal : animalsList) {
            if (animal == null) {
                throw new MyUncheckedException("Animal is null");
            }
        }
        if (animalsList.size() < 3) {
            throw new MyCheckedException("List must contain at least 3 elements");
        }
        List<Animal> animalList = new CopyOnWriteArrayList<>();

        animalsList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .forEach(animalList::add);
        return animalList;
    }
}
