package ru.mts.hw_4.service;

import ru.mts.hw_4.animal.Animal;

public interface SearchService {
    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     * @return Массив объектов класса String - имён животных
     */
    String[] findLeapYearNames(Animal[] animalArray);

    /**
     * Метод для нахождения в массиве животных тех животных,
     * которые старше N лет
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    Animal[] findOlderAnimals(Animal[] animalArray, int n);

    /**
     * Метод для нахождения дубликатов в массиве животных
     *
     * @param animalArray Массив животных - объектов, реализующих интерфейс Animal
     */
    void findDuplicates(Animal[] animalArray);
}
