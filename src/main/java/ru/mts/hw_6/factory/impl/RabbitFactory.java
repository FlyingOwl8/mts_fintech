package ru.mts.hw_6.factory.impl;

import ru.mts.hw_6.animal.Animal;
import ru.mts.hw_6.animal.impl.Rabbit;
import ru.mts.hw_6.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RabbitFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate) {
        return new Rabbit(breed, cost, birthDate);
    }
}
