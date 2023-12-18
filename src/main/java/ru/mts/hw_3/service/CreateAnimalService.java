package ru.mts.hw_3.service;

import ru.mts.hw_3.animal.Animal;
import ru.mts.hw_3.animal.impl.Deer;
import ru.mts.hw_3.animal.impl.Rabbit;
import ru.mts.hw_3.animal.impl.Shark;
import ru.mts.hw_3.animal.impl.Wolf;

import java.math.BigDecimal;

/**
 *  Интерфейс для создания различных объектов животных и вывода их характеристик
 */
public interface CreateAnimalService {
    /**
     * Дефолтный метод, создающий 10 разных объектов животных и печатающий их характеристики
     * Используется цикл while
     */
    default void createAnimals() {
        int i = 0;
        while (i < 10) {
            Animal animal;
            switch (i % 4) {
                case 0:
                    animal = new Wolf("gray", BigDecimal.valueOf((i + 5) * 3));
                    break;
                case 1:
                    animal = new Rabbit("white", BigDecimal.valueOf((i - 0.5) * 1.5));
                    break;
                case 2:
                    animal = new Shark("tiger", BigDecimal.valueOf((1.5 * i + 2) * 4));
                    break;
                case 3:
                    animal = new Deer("european", BigDecimal.valueOf((i + 0.5) * 2));
                    break;
                default:
                    animal = null;
            }
            System.out.println(animal);
            i++;
        }
        System.out.println("-----");
    }
}
