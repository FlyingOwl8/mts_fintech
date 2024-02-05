package ru.mts.hw_6.animal.impl;

import ru.mts.hw_6.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal {
    protected String name;
    protected String breed;
    protected String character;
    protected BigDecimal cost;
    protected LocalDate birthDate;

    /**
     * Метод получения названия животного
     *
     * @return Объект класса String - название животного
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения породы животного
     *
     * @return Объект класса String - порода животного
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Метод получения цены животного
     *
     * @return Объект класса BigDecimal - цена животного
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Метод получения характера животного
     *
     * @return Объект класса String - характер животного
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Метод получения даты рождения животного
     *
     * @return Объект класса LocalDate - дату рождения животного животного
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Метод проверки животных на идентичность
     *
     * @param object Объект класса Object - сравниваемое с данным животное
     * @return Значение типа boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object instanceof AbstractAnimal) {
            AbstractAnimal animal = (AbstractAnimal) object;
            return Objects.equals(name, animal.name) &&
                    Objects.equals(breed, animal.breed) &&
                    Objects.equals(character, animal.character) &&
                    Objects.equals(cost, animal.cost) &&
                    Objects.equals(birthDate, animal.birthDate);
        }
        return false;
    }

    /**
     * Хэш-функция для объектов животных
     *
     * @return Число типа int - хэш-код объекта животного
     */
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }

    protected void validateArguments(String breed, BigDecimal cost, LocalDate birthDate) {
        if (breed == null || cost == null || birthDate == null) {
            throw new IllegalArgumentException("Any field can't be null");
        }
        if (breed.isBlank()) {
            throw new IllegalArgumentException("Breed can't be an empty string");
        }
        if (cost.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException("Cost can't be negative");
        }
    }
}
