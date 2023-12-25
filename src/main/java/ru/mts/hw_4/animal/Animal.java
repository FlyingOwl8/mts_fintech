package ru.mts.hw_4.animal;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс для всех классов животных
 */

public interface Animal {
    /**
     * Метод получения названия животного
     *
     * @return Объект класса String - название животного
     */
    String getName();

    /**
     * Метод получения породы животного
     *
     * @return Объект класса String - порода животного
     */
    String getBreed();

    /**
     * Метод получения цены животного
     *
     * @return Объект класса BigDecimal - цена животного
     */
    BigDecimal getCost();

    /**
     * Метод получения характера животного
     *
     * @return Объект класса String - характер животного
     */
    String getCharacter();

    /**
     * Метод получения даты рождения животного
     *
     * @return Объект класса LocalDate - дату рождения животного животного
     */
    LocalDate getBirthDate();
}
