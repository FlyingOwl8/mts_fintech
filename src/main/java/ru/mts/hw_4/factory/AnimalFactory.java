package ru.mts.hw_4.factory;

import ru.mts.hw_4.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AnimalFactory {
    Animal createAnimal(AnimalTypes type, String breed, BigDecimal cost, LocalDate birthDate);
}
