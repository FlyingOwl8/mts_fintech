package ru.mts.hw_8.factory.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.config.AnimalNameConfig;
import ru.mts.hw_8.factory.AnimalFactory;
import ru.mts.hw_8.factory.AnimalTypes;
import ru.mts.hw_8.factory.ConcreteAnimalFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        List<String> wolfNames = animalNameConfig.getWolfNames();
        List<String> sharkNames = animalNameConfig.getSharkNames();
        List<String> rabbitNames = animalNameConfig.getRabbitNames();
        List<String> deerNames = animalNameConfig.getDeerNames();
        int i;
        switch (type) {
            case WOLF:
                i = ThreadLocalRandom.current().nextInt(0, wolfNames.size());
                return wolfFactory.createAnimal(wolfNames.get(i), breed, cost, birthDate);
            case SHARK:
                i = ThreadLocalRandom.current().nextInt(0, sharkNames.size());
                return sharkFactory.createAnimal(sharkNames.get(i), breed, cost, birthDate);
            case RABBIT:
                i = ThreadLocalRandom.current().nextInt(0, rabbitNames.size());
                return rabbitFactory.createAnimal(rabbitNames.get(i), breed, cost, birthDate);
            case DEER:
                i = ThreadLocalRandom.current().nextInt(0, deerNames.size());
                return deerFactory.createAnimal(deerNames.get(i), breed, cost, birthDate);
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
