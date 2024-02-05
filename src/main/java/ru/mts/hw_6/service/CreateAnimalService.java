package ru.mts.hw_6.service;

import ru.mts.hw_6.animal.Animal;
import ru.mts.hw_6.factory.AnimalFactory;
import ru.mts.hw_6.factory.AnimalTypes;
import ru.mts.hw_6.factory.impl.AnimalFactoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс для создания различных объектов животных и вывода их характеристик
 */
public interface CreateAnimalService {
    Animal getAnimal();

    void initAnimal();
    /**
     * Дефолтный метод, создающий 10 разных объектов животных
     * Используется цикл while
     *
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    default Animal[] createAnimals() {
        Animal[] animalArray = new Animal[10];
        AnimalFactory factory = new AnimalFactoryImpl();

        int i = 0;
        while (i < 10) {
            switch (i % 4) {
                case 0:
                    animalArray[i] = factory.createAnimal(AnimalTypes.WOLF, "gray", BigDecimal.valueOf((i + 5) * 3), LocalDate.ofYearDay(2023, (i * 2) % 365 + 1));
                    break;
                case 1:
                    animalArray[i] = factory.createAnimal(AnimalTypes.RABBIT, "white", BigDecimal.valueOf((i - 0.5) * 1.5), LocalDate.ofYearDay(2023, (i * 2) % 365 + 1));
                    break;
                case 2:
                    animalArray[i] = factory.createAnimal(AnimalTypes.SHARK, "tiger", BigDecimal.valueOf((1.5 * i + 2) * 4), LocalDate.ofYearDay(2023, (i * 2) % 365 + 1));
                    break;
                case 3:
                    animalArray[i] = factory.createAnimal(AnimalTypes.DEER, "european", BigDecimal.valueOf((i + 0.5) * 2), LocalDate.ofYearDay(2023, (i * 2) % 365 + 1));
                    break;
                default:
                    throw new IllegalStateException("Wrong ENUM type");
            }
            i++;
        }
        return animalArray;
    }
}
