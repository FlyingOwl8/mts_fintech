package ru.mts.hw_7.animal.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rabbit extends Prey {
    public Rabbit(String name, String breed, BigDecimal cost, LocalDate birthDate) {
        validateArguments(breed, cost, birthDate);
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.birthDate = birthDate;
    }
}
