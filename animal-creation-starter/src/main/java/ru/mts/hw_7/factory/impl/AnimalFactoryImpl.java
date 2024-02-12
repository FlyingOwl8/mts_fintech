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
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class AnimalFactoryImpl implements AnimalFactory {
    private final AnimalNameConfig animalNameConfig;
    private final ConcreteAnimalFactory wolfFactory;
    private final ConcreteAnimalFactory sharkFactory;
    private final ConcreteAnimalFactory deerFactory;
    private final ConcreteAnimalFactory rabbitFactory;

    public Animal createAnimal(AnimalTypes type, String breed, BigDecimal cost, LocalDate birthDate) {
        String[] wolfNames = animalNameConfig.getWolfNames();
        String[] sharkNames = animalNameConfig.getSharkNames();
        String[] rabbitNames = animalNameConfig.getRabbitNames();
        String[] deerNames = animalNameConfig.getDeerNames();
        int i;
        switch (type) {
            case WOLF:
                i = ThreadLocalRandom.current().nextInt(0, wolfNames.length);
                return wolfFactory.createAnimal(wolfNames[i], breed, cost, birthDate);
            case SHARK:
                i = ThreadLocalRandom.current().nextInt(0, sharkNames.length);
                return sharkFactory.createAnimal(sharkNames[i], breed, cost, birthDate);
            case RABBIT:
                i = ThreadLocalRandom.current().nextInt(0, rabbitNames.length);
                return rabbitFactory.createAnimal(rabbitNames[i], breed, cost, birthDate);
            case DEER:
                i = ThreadLocalRandom.current().nextInt(0, deerNames.length);
                return deerFactory.createAnimal(deerNames[i], breed, cost, birthDate);
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
