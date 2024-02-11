package ru.mts.hw_7.factory.impl;

import org.springframework.stereotype.Component;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.animal.impl.Deer;
import ru.mts.hw_7.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DeerFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String breed, BigDecimal cost, LocalDate birthDate) {
        return new Deer(breed, cost, birthDate);
    }
}
