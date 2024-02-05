package ru.mts.hw_6.factory.impl;

import ru.mts.hw_6.animal.Animal;
import ru.mts.hw_6.animal.impl.Shark;
import ru.mts.hw_6.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SharkFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate) {
        return new Shark(breed, cost, birthDate);
    }
}
