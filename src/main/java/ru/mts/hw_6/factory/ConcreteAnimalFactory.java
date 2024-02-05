package ru.mts.hw_6.factory;

import ru.mts.hw_6.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ConcreteAnimalFactory {
    Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate);
}
