package ru.mts.hw_7.factory.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.config.AnimalNameConfig;
import ru.mts.hw_7.factory.AnimalFactory;
import ru.mts.hw_7.factory.AnimalTypes;
import ru.mts.hw_7.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AnimalFactoryImpl implements AnimalFactory {
    private final AnimalNameConfig animalNameConfig;
    private final ConcreteAnimalFactory wolfFactory;
    private final ConcreteAnimalFactory sharkFactory;
    private final ConcreteAnimalFactory deerFactory;
    private final ConcreteAnimalFactory rabbitFactory;

    public Animal createAnimal(AnimalTypes type, String breed, BigDecimal cost, LocalDate birthDate) {
        String[] animalNames = animalNameConfig.getArrayOfStrings();
        switch (type) {
            case WOLF:
                return wolfFactory.createAnimal(breed, cost, birthDate);
            case SHARK:
                return sharkFactory.createAnimal(breed, cost, birthDate);
            case RABBIT:
                return rabbitFactory.createAnimal(breed, cost, birthDate);
            case DEER:
                return deerFactory.createAnimal(breed, cost, birthDate);
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
