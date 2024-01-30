package ru.mts.hw_4.factory.impl;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Shark;
import ru.mts.hw_4.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SharkFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate) {
        return new Shark(breed, cost, birthDate);
    }
}
