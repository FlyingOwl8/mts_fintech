package ru.mts.hw_7.factory;

import ru.mts.hw_7.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ConcreteAnimalFactory {
    Animal createAnimal(String name, String breed, BigDecimal cost, LocalDate birthDate);
}
