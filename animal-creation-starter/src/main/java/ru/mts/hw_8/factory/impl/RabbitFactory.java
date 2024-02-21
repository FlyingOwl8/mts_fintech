package ru.mts.hw_8.factory.impl;

import org.springframework.stereotype.Component;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.animal.impl.Rabbit;
import ru.mts.hw_8.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class RabbitFactory implements ConcreteAnimalFactory {
    public Animal createAnimal(String name, String breed, BigDecimal cost, LocalDate birthDate) {
        return new Rabbit(name, breed, cost, birthDate);
    }
}
