package ru.mts.hw_4.factory.impl;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Deer;
import ru.mts.hw_4.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeerFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate) {
        return new Deer(breed, cost, birthDate);
    }
}
