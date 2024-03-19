package ru.mts.hw.factory.impl;

import org.springframework.stereotype.Component;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.animal.impl.Wolf;
import ru.mts.hw.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class WolfFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String name, String breed, BigDecimal cost, LocalDate birthDate) {
        return new Wolf(name, breed, cost, birthDate);
    }
}
