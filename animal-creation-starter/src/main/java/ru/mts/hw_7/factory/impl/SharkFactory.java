package ru.mts.hw_7.factory.impl;

import org.springframework.stereotype.Component;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.animal.impl.Shark;
import ru.mts.hw_7.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SharkFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String name, String breed, BigDecimal cost, LocalDate birthDate) {
        return new Shark(name, breed, cost, birthDate);
    }
}
