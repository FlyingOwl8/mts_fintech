package ru.mts.hw_4.factory.impl;

import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Deer;
import ru.mts.hw_4.animal.impl.Rabbit;
import ru.mts.hw_4.animal.impl.Shark;
import ru.mts.hw_4.animal.impl.Wolf;
import ru.mts.hw_4.factory.AnimalFactory;
import ru.mts.hw_4.factory.AnimalTypes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnimalFactoryImpl implements AnimalFactory {
    public Animal createAnimal(AnimalTypes type, String breed, BigDecimal cost, LocalDate birthDate) {
        switch (type) {
            case WOLF:
                return new Wolf(breed, cost, birthDate);
            case SHARK:
                return new Shark(breed, cost, birthDate);
            case RABBIT:
                return new Rabbit(breed, cost, birthDate);
            case DEER:
                return new Deer(breed, cost, birthDate);
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
