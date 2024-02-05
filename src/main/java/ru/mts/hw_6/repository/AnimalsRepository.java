package ru.mts.hw_6.repository;

import ru.mts.hw_6.animal.Animal;

import java.util.List;

public interface AnimalsRepository {
//    void generateAnimals();

    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @return Массив объектов класса String - имён животных
     */
    String[] findLeapYearNames();

    /**
     * Метод для нахождения в массиве животных тех животных,
     * которые старше N лет
     *
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    Animal[] findOlderAnimals(int n);

    /**
     * Метод для нахождения дубликатов в массиве животных
     *
     */
    List<Animal> findDuplicates();

    void printDuplicates();
}
