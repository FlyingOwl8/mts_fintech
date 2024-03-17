package ru.mts.hw.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.exception.MyUncheckedException;
import ru.mts.hw.factory.AnimalTypes;
import ru.mts.hw.factory.impl.AnimalFactoryImpl;
import ru.mts.hw.service.CreateAnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactoryImpl factory;
    private Animal animalType;

    public Animal getAnimal() {
        return animalType;
    }

    public void initAnimal() {
        int i = ThreadLocalRandom.current().nextInt(1, 4);
        animalType = createNewAnimal(i);
    }
    /**
     * Перегруженный метод, создающий n различных объектов животных
     * Используется цикл for
     *
     * @param n число типа int, количество объектов для создания
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    public Map<String, List<Animal>> createAnimals(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        Animal newAnimal;
        for (int i = 0; i < n; i++) {
            newAnimal = createNewAnimal(i);
            String animalType = newAnimal.getBreed();
            if (animalsMap.containsKey(animalType)) {
                List<Animal> list = animalsMap.get(animalType);
                list.add(newAnimal);
            } else {
                List<Animal> list = new ArrayList<>();
                list.add(newAnimal);
                animalsMap.put(animalType, list);
            }
        }
        return animalsMap;
    }

    /**
     * Переопределённый метод, создающий 10 различных объектов животных
     * Используется цикл do-while
     *
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    @Override
    public Map<String, List<Animal>> createAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        Animal newAnimal;

        int i = 0;
        do {
            newAnimal = createNewAnimal(i);
            String animalType = newAnimal.getBreed();
            if (animalsMap.containsKey(animalType)) {
                List<Animal> list = animalsMap.get(animalType);
                list.add(newAnimal);
            } else {
                List<Animal> list = new ArrayList<>();
                list.add(newAnimal);
                animalsMap.put(animalType, list);
            }
            i++;
        } while (i < 10);
        return animalsMap;
    }

    private Animal createNewAnimal(int i) {
        switch (i % 4) {
            case 0:
                return factory.createAnimal(AnimalTypes.WOLF, "gray wolf", BigDecimal.valueOf((i + 5) * 3), LocalDate.ofYearDay(2020, (i * 2) % 365 + 1));
            case 1:
                return factory.createAnimal(AnimalTypes.RABBIT, "white rabbit", BigDecimal.valueOf((i - 0.5) * 1.5), LocalDate.ofYearDay(2020, (i * 2) % 365 + 1));
            case 2:
                return factory.createAnimal(AnimalTypes.SHARK, "tiger shark", BigDecimal.valueOf((1.5 * i + 2) * 4), LocalDate.ofYearDay(2020, (i * 2) % 365 + 1));
            case 3:
                return factory.createAnimal(AnimalTypes.DEER, "european deer", BigDecimal.valueOf((i + 0.5) * 2), LocalDate.ofYearDay(2020, (i * 2) % 365 + 1));
            default:
                throw new MyUncheckedException("Wrong ENUM type");
        }
    }
}
