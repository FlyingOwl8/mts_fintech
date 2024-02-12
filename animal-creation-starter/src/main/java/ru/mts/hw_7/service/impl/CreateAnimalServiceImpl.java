package ru.mts.hw_7.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.factory.AnimalFactory;
import ru.mts.hw_7.factory.AnimalTypes;
import ru.mts.hw_7.service.CreateAnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactory factory;
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
    public Animal[] createAnimals(int n) {
        Animal[] animalArray = new Animal[n];

        for (int i = 0; i < n; i++) {
            animalArray[i] = createNewAnimal(i);
        }
        return animalArray;
    }

    /**
     * Переопределённый метод, создающий 10 различных объектов животных
     * Используется цикл do-while
     *
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    @Override
    public Animal[] createAnimals() {
        Animal[] animalArray = new Animal[10];

        int i = 0;
        do {
            animalArray[i] = createNewAnimal(i);
            i++;
        } while (i < 10);
        return animalArray;
    }

    private Animal createNewAnimal(int i) {
//        AnimalFactory factory = new AnimalFactoryImpl();
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
                throw new IllegalStateException("Wrong ENUM type");
        }
    }
}
