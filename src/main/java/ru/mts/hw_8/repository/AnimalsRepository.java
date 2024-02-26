package ru.mts.hw_8.repository;

import ru.mts.hw_8.animal.Animal;

import java.time.LocalDate;
import java.util.Map;

public interface AnimalsRepository {
//    void generateAnimals();

    /**
     * Метод для нахождения в массиве животных имён тех животных,
     * которые родились в вискосный год
     *
     * @return Массив объектов класса String - имён животных
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Метод для нахождения в массиве животных тех животных,
     * которые старше N лет
     *
     * @param n           Число типа int - заданный возраст
     * @return Массив животных - объектов, реализующих интерфейс Animal
     */
    Map<Animal, Integer> findOlderAnimals(int n);

    /**
     * Метод для нахождения дубликатов в массиве животных
     *
     */
    Map<String, Integer> findDuplicates();

    void printDuplicates();
}
