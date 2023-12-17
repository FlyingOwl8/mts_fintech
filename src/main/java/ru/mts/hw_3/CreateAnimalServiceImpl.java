package ru.mts.hw_3;

import java.math.BigDecimal;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    /**
     * Перегруженный метод, создающий n различных объектов животных и печатающий их характеристики
     * Используется цикл for
     *
     * @param n - число типа int, количество объектов для создания
     */
    public void createAnimals(int n) {
        for (int i = 0; i < n; i++) {
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
        }
        System.out.println("-----");
    }

    /**
     * Переопределённый метод, создающий 10 различных объектов животных и печатающий их характеристики
     * Используется цикл do-while
     */
    @Override
    public void createAnimals() {
        int i = 0;
        do {
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
        } while (i < 10);
        System.out.println("-----");
    }
}
