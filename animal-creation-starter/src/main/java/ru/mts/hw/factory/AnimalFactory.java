package ru.mts.hw.factory;

import ru.mts.hw.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AnimalFactory {
    Animal createAnimal(AnimalTypes type, String breed, BigDecimal cost, LocalDate birthDate);
}
